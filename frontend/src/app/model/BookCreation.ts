export class BookCreation {
  title: String;
  preface: String;
  publisher: number;
  category: number;
  content: String;
  writers: Array<number>;
  maxPieces: number;

  constructor(title?: String, preface?: String, publisher?: number,
              category?: number, content?: String, writers?: Array<number>,
              maxPieces?: number) {

    this.title = title || '';
    this.preface = preface || '';
    this.publisher = publisher;
    this.category = category;
    this.content = content || '';
    this.writers = writers;
    this.maxPieces = maxPieces;
  }
}
