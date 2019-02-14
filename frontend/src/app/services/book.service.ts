import {Injectable} from '@angular/core';
import {BookDetails} from '../model/BookDetails';
import {Server, Routes} from '../utils/ServerRoutes';
import 'rxjs/add/operator/map';
import { HttpService } from './http.service';
import { BookCreationSelectLists } from '../model/BookCreationSelectLists';
import { Observable } from 'rxjs/Observable';
import { BookCreation } from '../model/BookCreation';
import { HttpParams } from '@angular/common/http';
import { RequestOptions } from '@angular/http';

@Injectable()
export class BookService {

  constructor(private http: HttpService) {
  }

  findAllAvailable(): Observable<BookDetails[]> {
    return this.http.get(Server.routeTo(Routes.BOOKS) + '/findAllAvailable')
      .map(res => res.json());
  }

  findAllRented(): Observable<BookDetails[]> {
    return this.http.get(Server.routeTo(Routes.BOOKS) + '/findAllRented')
      .map(res => res.json());
  }

  createBookCreationSelectLists(): Observable<BookCreationSelectLists> {
    return this.http.get(Server.routeTo(Routes.BOOKS) + '/createBookCreationSelectLists')
      .map(res => res.json());
  }

  create(book: BookCreation) {
    return this.http.post(Server.routeTo(Routes.BOOKS + '/create'), book)
      .map(res => res);
  }

  rent(id: number) {
    return this.http.post(Server.routeTo(Routes.BOOKS + '/rent/'), id)
      .map(res => res);
  }

  deleteRent(id: number) {
    return this.http.delete(Server.routeTo(Routes.BOOKS) + '/' + id)
      .map(res => res);
  }
}
