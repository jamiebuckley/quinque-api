create table game(
    id UUID not null,
    word text not null,
    solved bool not null,
    guesses text[]
);