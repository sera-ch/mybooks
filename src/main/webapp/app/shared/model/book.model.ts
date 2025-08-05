import { IAuthor } from '@/shared/model/author.model';

export interface IBook {
  id?: number;
  name?: string | null;
  author?: IAuthor | null;
}

export class Book implements IBook {
  constructor(public id?: number, public name?: string | null, public author?: IAuthor | null) {}
}
