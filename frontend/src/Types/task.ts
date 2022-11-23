export type Task = {
    id: number;
    name: string;
    title: string;
    subject: string;
    statusId: number;
    creationDate: Date;
}

export type TaskPage = {
    content: Task[];
    last: boolean;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}