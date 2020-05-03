import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login.component';
import { FormsModule } from '@angular/forms';
import {throwError as observableThrowError, of as observableOf,  Observable } from 'rxjs';
import { ComponentFixture, fakeAsync, tick, inject} from '@angular/core/testing';
import { Router } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Location } from '@angular/common';
import { By } from '@angular/platform-browser';
import { FormControl } from '@angular/forms';
import { NgForm } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatListModule } from '@angular/material/list';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTabsModule } from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthenticationService } from '@/_services/authentication.service';
import { AlertService } from '@/_services';

const testConfig = {
    error404: {
      message: 'Http failure response for http://localhost:3000/auth/v1: 404 Not Found',
      name: 'HttpErrorResponse',
      ok: false,
      status : 404,
      statusText: 'Not Found',
      url: 'http://localhost:3000/auth/v1'
    },
    error403: {
      error: {message: 'Unauthorized'},
      message: 'Http failure response for http://localhost:3000/auth/v1/: 403 Forbidden',
      name: 'HttpErrorResponse',
      ok: false,
      status: 403,
      statusText: 'Forbidden',
      url: 'http://localhost:3000/auth/v1/'
    },
    positive: {
      token: 'token123'
    }
  };
  

describe('LoginComponent', () => {
    let authenticationService: AuthenticationService;
  let positiveResponse: any;
  let loginComponent: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let spyAuthenticateUser: any;
  let spySetBearerToken: any;
  let spyRouteToDashboard: any;
  const routerSpy: any = {};
  let location: Location;
  let routerService: any;
  let router: any;
  let errorMessage: any;
  let debugElement: any;
  let element: any;
    
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        FormsModule,
        FormsModule,
      BrowserAnimationsModule,
      MatInputModule,
      MatAutocompleteModule,
      MatCheckboxModule,
      MatDatepickerModule,
      MatFormFieldModule,
      MatInputModule,
      MatRadioModule,
      MatSelectModule,
      MatSliderModule,
      MatSlideToggleModule,
      MatMenuModule,
      MatSidenavModule,
      MatToolbarModule,
      MatCardModule,
      MatExpansionModule,
      MatGridListModule,
      MatListModule,
      MatStepperModule,
      MatTabsModule,
      MatButtonModule,
      MatButtonToggleModule,
      MatChipsModule,
      MatIconModule,
      MatProgressSpinnerModule,
      MatProgressBarModule,
      MatDialogModule,
      MatSnackBarModule,
      MatTooltipModule,
      MatPaginatorModule,
      MatSortModule,
      MatTableModule,
      HttpClientModule,
      ReactiveFormsModule
      ],
      declarations: [
        LoginComponent
      ],
      providers: [
        AuthenticationService,
        AlertService
        ],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();
  }));

  it('should test the login page', async(() => {
    const fixture = TestBed.createComponent(LoginComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));

//   it('should handle to login into the system', fakeAsync(() => {
//     positiveResponse = testConfig.positive;
//     //spyAuthenticateUser = spyOn(authenticationService, 'login').and.returnValue(observableOf(positiveResponse));
//     const token = testConfig.positive.token;
    
//     //spyRouteToDashboard = spyOn(router, 'navigate').and.callFake(() => {});
//     // let formBuilder : any;
//     // loginComponent = this.formBuilder.group({
//     //     username: 'abc',
//     //     password: 'abc'
//     // });
//     loginComponent.onSubmit();
//     expect(localStorage.getItem('bearerToken')).toBe(token, 'should get token from local storage');
//   }));
});
