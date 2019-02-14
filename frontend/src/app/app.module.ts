import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {AppComponent} from './app.component';
import {LoginComponent} from './pages/login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthService} from './services/auth.service';
import {BookService} from './services/book.service';
import {HttpModule, XHRBackend, RequestOptions} from '@angular/http';
import {appRoutes} from './routes';
import {ErrorComponent} from './pages/error/error.component';
import {BookListComponent, BookContentDialog} from './pages/books/book-list/book-list.component';
import {HttpService} from './services/http.service';
import {JwtHelperService, JwtModule} from '@auth0/angular-jwt';
import { JwtConfig } from './config/jwt.config';
import { AuthGuard } from './guards/auth.guard';
import { PermissionGuard } from './guards/permission.guard';
import { UnAuthGuard } from './guards/unauth.guard';
import { MenuItemComponent } from './menu/components/menu/menu-item/menu-item.component';
import { MenuComponent } from './menu/components/menu/menu.component';
import { MenuItemGroupComponent } from './menu/components/menu/menu-item-group/menu-item-group.component';
import { SideMenuItemGroupComponent } from './menu/components/side-menu/side-menu-item-group/side-menu-item-group.component';
import { SideMenuItemComponent } from './menu/components/side-menu/side-menu-item/side-menu-item.component';
import { SideMenuComponent } from './menu/components/side-menu/side-menu.component';
import { LogoutComponent } from './pages/logout/logout.component';
import { MaterialModule } from './material.module';
import { BookCreateComponent } from './pages/books/book-create/book-create.component';

export function getToken(): string {
  return localStorage.getItem(JwtConfig.TOKEN);
}

export function createHttp(authService: AuthService, backend: XHRBackend, options: RequestOptions): HttpService {
  return new HttpService(authService, backend, options);
}

@NgModule({
  declarations: [
    BookContentDialog,
    AppComponent,
    BookCreateComponent,
    LogoutComponent,
    LoginComponent,
    BookListComponent,
    ErrorComponent,
    MenuItemComponent,
    MenuItemGroupComponent,
    MenuComponent,
    SideMenuItemComponent,
    SideMenuItemGroupComponent,
    SideMenuComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    JwtModule.forRoot({
      config: {
          tokenGetter: getToken
      }
  })
  ],
  entryComponents: [
    BookContentDialog
  ],
  providers: [
    AuthService,
    BookService,
    HttpService,
    UnAuthGuard,
    AuthGuard,
    PermissionGuard,
    JwtHelperService,
    {
      provide: HttpService,
      useFactory: createHttp,
      deps: [AuthService, XHRBackend, RequestOptions]
    }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
