import { Project } from "./project";
import { User } from "./user";

export class Task {
    id: number;
    title: string;
    rate: number;
    projectReview: Project | null;
    postedBy: User | null;
}