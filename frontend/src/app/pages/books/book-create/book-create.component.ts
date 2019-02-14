import {Component, Inject, OnInit} from '@angular/core';
import {DataSource} from '@angular/cdk/collections';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { BookDetails } from '../../../model/BookDetails';
import { BookService } from '../../../services/book.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { BookCreation } from '../../../model/BookCreation';
import { BookCreationSelectLists } from '../../../model/BookCreationSelectLists';

@Component({
  selector: 'app-book-create',
  templateUrl: './book-create.component.html',
  styleUrls: ['./book-create.component.css']
})
export class BookCreateComponent implements OnInit {

  bookCreationSelectLists = new BookCreationSelectLists();

  bookForm: FormGroup = new FormGroup({
    title: new FormControl('', [Validators.required]),
    maxPieces: new FormControl('', [Validators.required, Validators.max(10)]),
    publisher: new FormControl('', [Validators.required]),
    category: new FormControl('', [Validators.required]),
    preface: new FormControl('', [Validators.required]),
    content: new FormControl('', [Validators.required]),
    writers: new FormControl(new Array<number>(), [Validators.required])
  });

  constructor(private bookService: BookService, private router: Router) {
  }

  ngOnInit() {
    this.initSelectList();
    this.title.setErrors({'incorrect': true});
  }

  submit() {
    this.bookService.create(this.create())
      .subscribe(
        res => this.router.navigate(['/books']),
        err => console.log(err));
  }

  create() {
    return new BookCreation(
      this.title.value,
      this.preface.value,
      this.publisher.value,
      this.category.value,
      this.content.value,
      this.writers.value,
      this.maxPieces.value);
  }

  private initSelectList() {
    this.bookService.createBookCreationSelectLists()
      .subscribe(
        res => this.bookCreationSelectLists = res,
        err => console.log(err));
  }

  get title() {
    return this.bookForm.get('title');
  }

  get maxPieces() {
    return this.bookForm.get('maxPieces');
  }

  get preface() {
    return this.bookForm.get('preface');
  }

  get publisher() {
    return this.bookForm.get('publisher');
  }

  get category() {
    return this.bookForm.get('category');
  }

  get content() {
    return this.bookForm.get('content');
  }

  get writers() {
    return this.bookForm.get('writers');
  }
}
