import {Injectable} from '@angular/core';
import {JwtConfig} from '../config/jwt.config';
import {JwtHelperService} from '@auth0/angular-jwt';
import {Http} from '@angular/http';
import decode from 'jwt-decode';
import { Server, Routes } from '../utils/ServerRoutes';
import { User } from '../model/User';
import { HttpService } from './http.service';

@Injectable()
export class AuthService {

  constructor(private http: Http,
              private jwtHelper: JwtHelperService) {
  }

  login(user: User) {
    return this.http.post(Server.routeTo(Routes.LOGIN), user)
      .map(res => {
        console.log(res.json().token);
        this.setToken(res.json().token);
      });
  }

  logout(): void {
    this.removeToken();
  }

  removeToken(): void {
    localStorage.removeItem(JwtConfig.TOKEN);
  }

  isAuthenticated(): boolean {
    const token = this.getEncodedToken();
    if (token == null) {
      return false;
    }
    return !this.jwtHelper.isTokenExpired(token);
  }

  setToken(token: any): void {
    localStorage.setItem(JwtConfig.TOKEN, token);
  }

  getEncodedToken() {
    return localStorage.getItem(JwtConfig.TOKEN);
  }

  getDecodedToken() {
    return decode(this.getEncodedToken());
  }

  getUsername(): string {
    if (this.isAuthenticated()) {
      return this.getDecodedToken().sub;
    }
    return null;
  }

  getPermissions(): Array<string> {
    if (this.isAuthenticated()) {
      return this.getDecodedToken().permissions;
    }
    return null;
  }

  hasPermission(permission: string): boolean {
    const permissions = this.getPermissions();
    if (permissions != null) {
      return permissions.includes(permission);
    }
    return null;
  }
}
