import { IAuthor } from '@/shared/model/author.model';

import { ReadStatus } from '@/shared/model/enumerations/read-status.model';
export interface IBook {
  id?: number;
  name?: string | null;
  readStatus?: ReadStatus | null;
  author?: IAuthor | null;
}

export class Book implements IBook {
  constructor(public id?: number, public name?: string | null, public readStatus?: ReadStatus | null, public author?: IAuthor | null) {}
}
