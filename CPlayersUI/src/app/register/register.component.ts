import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AlertService, UserService, AuthenticationService } from '@/_services';
import { User } from '@/_models';


// array in local storage for registered users
//let users = JSON.parse(localStorage.getItem('users')) || [];


@Component({templateUrl: 'register.component.html'})
export class RegisterComponent implements OnInit {
    registerForm: FormGroup;
    loading = false;
    submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authenticationService: AuthenticationService,
        private userService: UserService,
        private alertService: AlertService
    ) { 
        // redirect to home if already logged in
        if (this.authenticationService.currentUserValue) { 
            this.router.navigate(['/']);
        }
    }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            userName: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(10)]],
            confirmPassword: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(10)]],
        }
        ,
        {
            validator: this.allValidation
        });
    }

    allValidation(AC: AbstractControl) {
        let firstName = AC.get('firstName').value;
        let lastName = AC.get('lastName').value;
        let password = AC.get('password').value;
        let confirmPassword = AC.get('confirmPassword').value;

        //firtname check
        if(/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/.test(firstName) || /\d/.test(firstName)) {
            AC.get('firstName').setErrors({NameRegex: true});
        }

        //lastname check
        if(/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/.test(lastName) || /\d/.test(lastName)) {
            AC.get('lastName').setErrors({NameRegex: true});
        }

        //password regex
        if(!(/\d/.test(password) && /[A-Z]/.test(password) && /[a-z]/.test(password) && /[0-9]/.test(password))) {
            AC.get('password').setErrors({PasswordRegex: true});
        }

        //password should match confirmPassword
        if(password != confirmPassword) {
            AC.get('confirmPassword').setErrors( {MatchPassword: true} )
        } else {
            return null
        }
    }

    // convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.registerForm.invalid) {
            return;
        }

        this.loading = true;
        this.userService.register(this.registerForm.value)
            //.pipe(first())
            .subscribe(
                data => {
                    console.log(data);
                    this.alertService.success('Registration successful', true);
                    //alert('Registration successful');
                    this.router.navigate(['/login']);
                },
                error => {
                    this.alertService.error("User already exists");
                    this.loading = false;
                });
    }
}
