import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {AuthService} from '../services/auth.service';

@Injectable()
export class UnAuthGuard implements CanActivate, CanActivateChild {

  constructor(public auth: AuthService, public router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.auth.isAuthenticated()) {
      this.router.navigate(['home']);
      return false;
    }
    return true;
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot):
      boolean | Promise<boolean> | Observable<boolean> {
    return this.canActivate(childRoute, state);
  }
}
