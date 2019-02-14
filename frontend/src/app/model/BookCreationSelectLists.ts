import { CodeDictionaryItem } from './CodeDictionaryItem';

export class BookCreationSelectLists {

  publishers: Array<CodeDictionaryItem>;
  categories: Array<CodeDictionaryItem>;
  writers: Array<CodeDictionaryItem>;

  constructor(publishers?: Array<CodeDictionaryItem>,
              categories?: Array<CodeDictionaryItem>,
              writers?: Array<CodeDictionaryItem>) {

  this.publishers = publishers;
  this.categories = categories;
  this.writers = writers;
  }
}
