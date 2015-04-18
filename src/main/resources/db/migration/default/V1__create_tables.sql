CREATE TABLE animator (
  animator_id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  wikipedia_site_url varchar(255) NOT NULL,
  PRIMARY KEY (animator_id)
) ROW_FORMAT=COMPRESSED;

CREATE TABLE company (
  company_id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  company_type int(11) NOT NULL,
  PRIMARY KEY (company_id)
) ROW_FORMAT=COMPRESSED;

CREATE TABLE image (
  image_id varchar(255) NOT NULL,
  content mediumblob NOT NULL,
  PRIMARY KEY (image_id)
) ROW_FORMAT=COMPRESSED;

CREATE TABLE season (
  year int(11) NOT NULL,
  season_type int(11) NOT NULL,
  PRIMARY KEY (year,season_type)
) ROW_FORMAT=COMPRESSED;

CREATE TABLE user (
  user_id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  facebook_user_id bigint(20) DEFAULT NULL,
  google_user_id bigint(20) DEFAULT NULL,
  twitter_user_id bigint(20) DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id),
  UNIQUE KEY unique_facebook_user_id (facebook_user_id)
) ROW_FORMAT=COMPRESSED;


CREATE TABLE anime (
  anime_id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  year int(11) NOT NULL,
  season_type int(11) NOT NULL,
  production_company_id bigint(20) NOT NULL,
  director_id bigint(20) DEFAULT NULL,
  original_piece varchar(255) NOT NULL,
  official_site_url varchar(255) NOT NULL,
  wikipedia_site_url varchar(255) NOT NULL,
  PRIMARY KEY (anime_id),
  KEY production_company_id (production_company_id),
  KEY director_id (director_id),
  FOREIGN KEY (production_company_id) REFERENCES company (company_id),
  FOREIGN KEY (director_id) REFERENCES animator (animator_id)
) ROW_FORMAT=COMPRESSED;
