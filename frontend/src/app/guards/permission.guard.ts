import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class PermissionGuard implements CanActivate, CanActivateChild {

  constructor(private authService: AuthService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['login']);
      return false;
    }

    const expectedPermission = route.data.permission;
    if (!this.authService.hasPermission(expectedPermission)) {
      this.router.navigate(['login']);
      return false;
    }

    return true;
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot):
    boolean | Promise<boolean> | Observable<boolean> {
    return this.canActivate(childRoute);
  }
}
