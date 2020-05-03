import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { User } from '@/_models';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UserService {
    public apikey = '97zRxJ943BbqDtF84RIDoWAVvo72';
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(`${config.apiUrl}/users`);
    }

    getById(id: number) {
        return this.http.get(`${config.apiUrl}/users/${id}`);
    }

    register(user: User) {
        //return this.http.post(`${config.apiUrl}/users/register`, user);
        return this.http.post(`http://localhost:8765/userservice/api/v1/auth/register`, user).pipe(map(res => res));
    }

    update(user: User) {
        return this.http.put(`${config.apiUrl}/users/${user.id}`, user);
    }

    delete(id: number) {
        return this.http.delete(`${config.apiUrl}/users/${id}`);
    }

    findPlayersWithName(playername: string)  {
        return this.http.get(`https://cricapi.com/api/playerFinder?apikey=${this.apikey}&name=${playername}`)
        .pipe(map((res) => res['data']));
    }
}