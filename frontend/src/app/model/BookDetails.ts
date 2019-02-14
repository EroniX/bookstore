export class BookDetails {
  
  id: number;
  title: String;
  publisher: String;
  category: String;
  content: String;
  writers: Array<String>;
  maxPieces: number;
  availablePieces: number;
  rented: boolean;

  constructor(id?: number, title?: String, publisher?: String, category?: String, content?: String,
              writers?: Array<String>, maxPieces?: number, availablePieces?: number, rented?: boolean) {
    this.id = id;
    this.title = title || '';
    this.publisher = publisher || '';
    this.category = category || '';
    this.content = content || '';
    this.writers = writers;
    this.maxPieces = maxPieces;
    this.availablePieces = availablePieces;
    this.rented = rented;
  }
}
