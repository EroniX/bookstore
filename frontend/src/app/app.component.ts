import { Component, OnInit } from '@angular/core';
import { Menu } from './menu/model/menu';
import { MenuConfig } from './menu/menu.config';
import { AuthService } from './services/auth.service';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  mainMenu: Menu;
  rightMenu: Menu;

  title = 'app';

  constructor(private authService: AuthService, private router: Router) {
    this.loadMainMenu();
    this.loadRightMenu();
  }

  loadMainMenu(): void {
    this.mainMenu = MenuConfig.createMainMenu(
      this.authService.isAuthenticated(),
      this.authService.getPermissions()
    );
  }

  loadRightMenu(): void {
    this.rightMenu = MenuConfig.createRightMenu(
      this.authService.isAuthenticated(),
      this.authService.getPermissions()
    );
  }

  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.loadMainMenu();
        this.loadRightMenu();
      }
    });
  }
}
