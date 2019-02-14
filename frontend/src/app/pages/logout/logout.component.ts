import {Router} from '@angular/router';
import {Component} from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'logout',
  template: ''
})
export class LogoutComponent {
  constructor(private authService: AuthService,
              private router: Router) {

    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
