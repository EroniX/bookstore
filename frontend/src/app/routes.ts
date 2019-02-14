import {Routes} from '@angular/router';
import {LoginComponent} from './pages/login/login.component';
import {ErrorComponent} from './pages/error/error.component';
import { BookListComponent } from './pages/books/book-list/book-list.component';
import { UnAuthGuard } from './guards/unauth.guard';
import { LogoutComponent } from './pages/logout/logout.component';
import { AuthGuard } from './guards/auth.guard';
import { BookCreateComponent } from './pages/books/book-create/book-create.component';

export const appRoutes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [UnAuthGuard]
  },
  {
    path: 'logout',
    component: LogoutComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'books',
    component: BookListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'books/create',
    component: BookCreateComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    component: ErrorComponent
  }
];
