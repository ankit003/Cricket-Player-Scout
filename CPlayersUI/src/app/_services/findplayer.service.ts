import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Favourite } from '@/_models/favourite';
import { PlayerRecommendation } from '@/_models/playerRecommendation';


@Injectable({ providedIn: 'root' })
export class FindplayerService {
    public apikey = '97zRxJ943BbqDtF84RIDoWAVvo72';

    constructor(private http: HttpClient) {
    }

    findPlayersWithName(playername: string)  {
        return this.http.get(`https://cricapi.com/api/playerFinder?apikey=${this.apikey}&name=${playername}`)
        .pipe(map((res) => res['data']));
    }

    // return this.httpClient.post('http://localhost:3000/auth/v1/isAuthenticated', {}, {
    //   headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
    // }).pipe(map((response) => response['isAuthenticated']))
    //   .toPromise();

    getPlayerStats(pid: number)  {
        return this.http.get(`https://cricapi.com/api/playerStats?apikey=${this.apikey}&pid=${pid}`)
        .pipe(map((res) => res));
    }

    addToFavourite(favourite: Favourite)  {
        return this.http.post(`http://localhost:8765/favouriteservice/favourite/addToFavourite`, favourite, {
            headers: new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`)
        });
    }

    removeFavourite(favourite: Favourite)  {
        return this.http.delete(`http://localhost:8765/favouriteservice/favourite/removeFavourite/${favourite.userName}/${favourite.playerId}`);
    }

    addPlayerRecommendation(playerRecommendation: PlayerRecommendation)  {
        return this.http.post(`http://localhost:8765/playerrecommendationservice/playerRecommendation/addPlayerRecommendation`, playerRecommendation);
    }

    viewFavourites(username: string) {
        return this.http.get<Favourite[]>(`http://localhost:8765/favouriteservice/favourite/viewFavourites/${username}`)
        .pipe(map((res) => res));
    }

    viewRecommendations() {
        return this.http.get<PlayerRecommendation[]>(`http://localhost:8765/playerrecommendationservice/playerRecommendation/viewPlayerRecommendations`)
        .pipe(map((res) => res));
    }
}