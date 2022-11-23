export type Chart = {
    id: number;
    name: string;
    taskList: [];
}

export type ChartPage = {
    content: Chart[];
    last: boolean;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}