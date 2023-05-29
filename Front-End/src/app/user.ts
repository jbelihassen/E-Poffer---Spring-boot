import { Project } from "./project";

export class User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    password: string;
    projects : Project[] ;
    userProjects : Project[] ;


}
