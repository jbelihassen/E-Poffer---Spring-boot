import { FileHandel } from "./imagesFile";
import { User } from "./user";


export class Project {
    id: number;
    title: String;
    description: String;
    iPrice: number;
    oPrice: number;
    cOffer: number;
    categorie: String;
    dueDate: Date;
    projectImages : FileHandel[];
    posted_user:User | null;
    joinedUsers: User[]  ;
}

