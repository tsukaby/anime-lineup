/*
-- For H2 Database
create table anime (
  anime_id bigserial not null primary key,
  name varchar(255) not null,
  year int not null,
  season_type int not null,
  production_company_id bigint not null,
  director_id bigint,
  original_piece varchar(255) not null,
  official_site_url varchar(255) not null,
  wikipedia_site_url varchar(255) not null,
  production_company_id other,
  director_id other
)

*/