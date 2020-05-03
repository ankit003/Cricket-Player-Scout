import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { first, map } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { User } from '@/_models';
import { UserService, AuthenticationService, AlertService } from '@/_services';

import { } from '../_services';
import { forEach } from '@angular/router/src/utils/collection';
import { Findplayers } from '@/_models/findplayers';
import { FindplayerService } from '@/_services/findplayer.service';
import { Player } from '@/_models/player';
import { Favourite } from '@/_models/favourite'
import { PlayerRecommendation } from '@/_models/playerRecommendation';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent implements OnInit, OnDestroy {
    currentUser: User;
    currentUserSubscription: Subscription;
    users: User[] = [];
    loginForm: FormGroup;
    displayPlayerStats = false;
    submitted = false;
    players: Findplayers[] = [];
    displayPlayerList = false;
    selectedPlayer: Findplayers;
    displayFavouriteList = false;
    playerStats : Player;
    favourite : Favourite;
    favouritePlayers : Favourite[] = [];
    playerRecommendation : PlayerRecommendation;
    recommendedPlayers : PlayerRecommendation[] = [];
    displayRecommendationList = false;

    constructor(
        private authenticationService: AuthenticationService,
        private userservice: UserService,
        private formBuilder: FormBuilder,
        private findplayerservice: FindplayerService,
        private alertService: AlertService
    ) 
    {
        this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
        this.currentUser = user;
    
        this.playerStats = new Player();
        this.favourite = new Favourite();
        this.playerRecommendation = new PlayerRecommendation();
    });
    }

    ngOnInit() {
        // this.loadAllUsers();
        this.loginForm = this.formBuilder.group({
            playername: ['', Validators.required]
        });
    }

    ngOnDestroy() {
        this.currentUserSubscription.unsubscribe();
    }

    get f() { return this.loginForm.controls; }

    // deleteUser(id: number) {
    //     this.userService.delete(id).pipe(first()).subscribe(() => {
    //         this.loadAllUsers()
    //     });
    // }

    // private loadAllUsers() {
    //     this.userService.getAll().pipe(first()).subscribe(users => {
    //         this.users = users;
    //     });
    // }

    onSearch() {
        this.submitted = true;

        let data = this.findplayerservice.findPlayersWithName(this.f.playername.value)
        .subscribe(res => {
            this.players = res;
            this.displayPlayerList = true;
            this.displayPlayerStats = false;
            this.displayFavouriteList = false;
            this.displayRecommendationList = false;
        });    
    }

    onPlayerSelect(pid: number) {    
        let data = this.findplayerservice.getPlayerStats(pid)
        .subscribe(res => {
            this.playerStats.pid = pid;
            this.playerStats.profile = res['profile'];
            this.playerStats.imageURL = res['imageURL'];
            this.playerStats.battingStyle = res['battingStyle'];
            this.playerStats.bowlingStyle = res['bowlingStyle'];
            this.playerStats.majorTeams = res['majorTeams'];
            this.playerStats.currentAge = res['currentAge'];
            this.playerStats.born = res['born'];
            this.playerStats.fullName = res['fullName'];
            this.playerStats.country = res['country'];

            this.playerStats.playingRole = res['playingRole'];
            this.displayPlayerList = false;
            this.displayPlayerStats = true;
        });    
    }

    addToFavourite() {
        this.favourite.userName = this.currentUser.userName;
        this.favourite.playerId = this.playerStats.pid;
        this.favourite.playerName = this.playerStats.fullName;
        this.findplayerservice.addToFavourite(this.favourite)
        .subscribe(
            success => {
                this.alertService.success("Added " + this.favourite.playerName + " to Favourites");
                //alert("Added " + this.favourite.playerName + " to Favourites");
                this.displayPlayerStats = false;
                /* this.playerRecommendation = new PlayerRecommendation();
                this.playerRecommendation.playerId = this.favourite.playerId;
                this.playerRecommendation.playerName = this.favourite.playerName;
                this.playerRecommendation.playerRecommendationCounter = -1;
                this.findplayerservice.addPlayerRecommendation(this.playerRecommendation)
                .subscribe(
                    success => console.log('Add Recommendation success'),
                    error => console.log('Add Recommendation error')
                ); */
            },
            error => {
                this.alertService.error("Could not add " + this.favourite.playerName + " to Favourites");
                //alert("Could not add " + this.favourite.playerName + " to Favourites");
                this.displayPlayerStats = false;               
            }
        )
    }

    removeFavourite() {
        this.favourite.userName = this.currentUser.userName;
        this.favourite.playerId = this.playerStats.pid;
        this.favourite.playerName = this.playerStats.fullName;
        this.findplayerservice.removeFavourite(this.favourite)
        .subscribe(
            success => {
                this.alertService.success("Removed " + this.favourite.playerName + " from Favourites");
                this.displayPlayerStats = false;
                this.displayFavouriteList = false;
            },
            error => {
                this.alertService.error("Could not remove " + this.favourite.playerName + " from Favourites");
                this.displayPlayerStats = false;               
            }
        )
    }

    viewFavourites() {
        this.findplayerservice.viewFavourites(this.currentUser.userName)
        .subscribe(
            res => {
                this.favouritePlayers = res;
                this.displayFavouriteList = true;
                this.displayPlayerList = false;
                this.displayPlayerStats = false;
                this.displayRecommendationList = false;
            },
            error => {
                this.alertService.error("Favourite players not found. Please add Favourites.");
                //alert("Favourite players not found. Please add Favourites.");
            }
        )
    }

    viewRecommendations() {
        this.findplayerservice.viewRecommendations()
        .subscribe(
            res => {
                this.recommendedPlayers = res;
                this.displayRecommendationList = true;
                this.displayPlayerList = false;
                this.displayPlayerStats = false;
                this.displayFavouriteList = false;
            },
            error => {
                this.alertService.error("Recommended players not found. Please add Favourites first.");
                //alert("Recommended players not found. Please add Favourites first.");
            }
        )
    }
}