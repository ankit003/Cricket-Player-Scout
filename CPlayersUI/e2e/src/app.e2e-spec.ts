// import { AppPage } from './app.po';
// import { browser, element, by } from 'protractor';

// describe('initial page', () => {


//   it('should navigate to home (login page) as default page on loading app',() => {
//     browser.get('/');
//     expect(browser.getCurrentUrl()).toContain('home');
//     browser.sleep(3000);
//   })
// });

import {browser, element, by} from 'protractor';
import { protractor } from 'protractor/built/ptor';

describe('CPlayersUI E2E Tests', () =>{

  it('should load login page on app launch', () => {
    browser.get('/');
    expect(browser.getCurrentUrl()).toContain('login');
  });

  it('on login it should give error if user not present', () => {
    browser.get('/');
    expect(browser.getCurrentUrl()).toContain('login');
    const inputElements = element.all(by.css('input'));
    inputElements.get(0).sendKeys('NewUser');
    inputElements.get(1).sendKeys('Newuser1');
    element.all(by.css('button')).click();
    expect(browser.getCurrentUrl()).toContain('login');
  });

  it('should be able to register new user', () => {
    browser.get('/register');
    expect(browser.getCurrentUrl()).toContain('register');
    const inputElements = element.all(by.css('input'));
    inputElements.get(0).sendKeys('Fname');
    inputElements.get(1).sendKeys('Lname');
    inputElements.get(2).sendKeys('E2Ename2');
    inputElements.get(3).sendKeys('E2Epass');
    inputElements.get(4).sendKeys('E2Epass');
    element.all(by.css('button')).click();
    expect(browser.getCurrentUrl()).toContain('login');
  });

  it('should redirect to home after successful login', () => {
    browser.get('/login')
    const inputElements = element.all(by.css('input'));
    inputElements.get(0).sendKeys('E2Ename2');
    inputElements.get(1).sendKeys('E2Epass');
    element.all(by.css('button')).click();
    expect(browser.getCurrentUrl()).toContain('home');
  });

  it('should be able to search for players', (done) => {
    //element.all(by.css('mat-panel-title')).get(0).click();
    const inputElements = element.all(by.css('input'));
    browser.sleep(1000);
    inputElements.get(0).sendKeys('Virat');
    browser.sleep(1000);
    element.all(by.css('button')).get(0).click();
    browser.sleep(3000).then(() => {
      expect(element.all(by.css('mat-action-list')).isPresent()).toBe(true);
    }); 
    done();
  });

  it('should be able to show to player stats on clicking a player from list', (done) => {
    let players = element.all(by.id('player-list-button'));
    players.get(0).click();
    browser.sleep(1000);
    expect(element.all(by.css('mat-card')).first().isDisplayed()).toBe(true);
    done();
  });

  it('should be able to Add to favourites list', (done) => {
      element(by.partialButtonText('Add to Favourite')).click();
      browser.sleep(3000);
      expect(element(by.css('mat-card')).isPresent()).toBeFalsy();
      done();
  });

  it('should be able to view favourites list and check player is present', (done) => {
    element(by.partialButtonText('View Favourites')).click();
    expect(element.all(by.css('mat-action-list')).isPresent()).toBe(true);
    expect(element(by.partialButtonText('Senerath Seneviratne')).isPresent()).toBe(true);
    done();
  });

  it('should be able to show to player stats on clicking a player from favourite list', (done) => {
    let players = element.all(by.id('favourite-player-list-button'));
    players.get(0).click();
    browser.sleep(1000);
    expect(element.all(by.css('mat-card')).first().isDisplayed()).toBe(true);
    done();
  });

  it('should be able to delete player from favourite list', (done) => {
    element(by.partialButtonText('Remove from Favourite')).click();
    browser.sleep(3000);
    expect(element(by.css('mat-card')).isPresent()).toBeFalsy();
    done();
  });

  it('should be able to view the recommended players', (done) => {
    element(by.partialButtonText('View Recommendations')).click();
    expect(element.all(by.css('mat-action-list')).isPresent()).toBe(true);

    //When favourite is added it is added to recommendation list as well
    browser.sleep(1000);
    expect(element(by.partialButtonText('Senerath Seneviratne')).isPresent()).toBe(true);
    done();
  });

  it('should be able to show to player stats on clicking a player from recommended list', (done) => {
    let players = element.all(by.id('recommended-player-list-button'));
    players.get(0).click();
    browser.sleep(1000);
    expect(element.all(by.css('mat-card')).first().isDisplayed()).toBe(true);
    done();
  });

  it('should be able to Add to favourites from recommended player expanded stats', (done) => {
    element(by.partialButtonText('Add to Favourite')).click();
    browser.sleep(3000);
    expect(element(by.css('mat-card')).isPresent()).toBeFalsy();
    done();
  });

  it('should be able to logout of application', (done) => {
    element.all(by.id('logout')).first().click();
    browser.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('login');
    done();
  });

});