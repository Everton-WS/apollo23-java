-- Table for events
CREATE TABLE event_manager.events (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  website VARCHAR(255),
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  city VARCHAR(255),
  state VARCHAR(255)
);

-- Table for users
CREATE TABLE event_manager.users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255) UNIQUE,
  mobile VARCHAR(255),
  password VARCHAR(255)
);

-- Table for users_events


-- Table for activities
CREATE TABLE event_manager.activities (
  id SERIAL PRIMARY KEY,
  event_id INT,
  speaker_id INT,
  type VARCHAR(255),
  name VARCHAR(255),
  description TEXT,
  date TIMESTAMP,
  start_time TIME,
  end_time TIME,
  location VARCHAR(255)
);

-- Table for speakers
CREATE TABLE event_manager.speakers (
  id SERIAL PRIMARY KEY,
  event_id INT,
  name VARCHAR(255),
  social_media VARCHAR(255),
  mini_bio TEXT
);

-- Table for messages
CREATE TABLE event_manager.messages (
  id SERIAL PRIMARY KEY,
  event_id INT,
  sender_id INT,
  recipient_id INT,
  text TEXT,
  date_time TIMESTAMP
);

-- Table for questions
CREATE TABLE event_manager.questions (
  id SERIAL PRIMARY KEY,
  event_id INT,
  activity_id INT,
  speaker_id INT,
  user_id INT,
  question_text TEXT,
  approved BOOLEAN,
  excluded BOOLEAN
);

-- Table for treasures
CREATE TABLE event_manager.treasures (
  id SERIAL PRIMARY KEY,
  event_id INT,
  activity_id INT,
  token VARCHAR(255),
  score INT,
  hidden BOOLEAN
);

CREATE TABLE event_manager.users_events (
  id SERIAL PRIMARY KEY,
  user_id INT,
  event_id INT
);

CREATE TABLE event_manager.users_activities (
  id SERIAL PRIMARY KEY,
  user_id INT,
  activity_id INT
);

CREATE TABLE event_manager.users_treasures (
  id SERIAL PRIMARY KEY,
  user_id INT,
  treasure_id INT,
  date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);