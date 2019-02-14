import {Component, Inject} from '@angular/core';
import {DataSource} from '@angular/cdk/collections';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { BookDetails } from '../../../model/BookDetails';
import { BookService } from '../../../services/book.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent {
  displayedColumns: String[] =
    ['title', 'publisher', 'category', 'writers', 'availablePieces', 'operation'];
  availableBooks: DataSource<any> = new AvailableBooksDataSource(this.bookService);
  rentedBooks: DataSource<any> = new RentedBooksDataSource(this.bookService);

  constructor(private bookService: BookService, public dialog: MatDialog) {
  }

  openDialog(content: string): void {
    const dialogRef = this.dialog.open(BookContentDialog, {
      width: '450px',
      data: content
    });
  }

  rent(id: number) {
    this.bookService.rent(id).subscribe(
      res => window.location.reload(),
      err => console.log(err));
  }

  deleteRent(id: number) {
    this.bookService.deleteRent(id).subscribe(
      res => window.location.reload(),
      err => console.log(err));
  }
}

export class AvailableBooksDataSource extends DataSource<any> {
  constructor(private bookService: BookService) {
    super();
  }

  connect(): Observable<BookDetails[]> {
    return this.bookService.findAllAvailable();
  }

  disconnect() {
  }
}

export class RentedBooksDataSource extends DataSource<any> {
  constructor(private bookService: BookService) {
    super();
  }

  connect(): Observable<BookDetails[]> {
    return this.bookService.findAllRented();
  }

  disconnect() {
  }
}

@Component({
  selector: 'book-content-dialog',
  templateUrl: 'book-content.component.html',
  styleUrls: ['book-content.component.css'],
})
export class BookContentDialog {

  constructor(
    public dialogRef: MatDialogRef<BookContentDialog>,
    @Inject(MAT_DIALOG_DATA) public data: string) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
