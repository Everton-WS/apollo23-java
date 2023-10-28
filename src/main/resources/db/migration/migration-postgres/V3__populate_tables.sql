-- Insert mock data into the events table
INSERT INTO event_manager.events (name, website, start_date, end_date, city, state)
VALUES
  ('Hackweek', 'www.devs2blu.com.br', '2023-05-01 09:00:00', '2023-10-31 17:00:00', 'Blumenau', 'Santa Catarina'),
  ('Oktobertech', 'www.oktobertech.org', '2023-10-05 10:00:00', '2023-10-20 18:00:00', 'Blumenau', 'Santa Catarina');

-- Insert mock data into the users table
INSERT INTO event_manager.users (name, email, mobile, password)
VALUES
  ('user', 'user@apollo23.com', '123-456-7890', 'password1'),
  ('admin', 'admin@apollo23.com', '987-654-3210', 'password2');


-- Insert mock data into the speakers table
INSERT INTO event_manager.speakers (event_id, name, social_media, mini_bio)
VALUES
  (1, 'Professor Baita', 'twitter.com/baita', 'Professor com anos de carreira, dando aulas e ministrando palestras.'),
  (1, 'Professora Top', 'facebook.com/top', 'Empreendedora, influenciadora e mentora de novos talentos.'),
  (2, 'Palestrante Referência', 'linkedin.com/referência', 'Há anos criando cursos e desenvolvendo eventos.'),
  (2, 'Empresário Renomado', 'linkedin.com/renomado', 'Empreendedor experiente, liderou a concepção e gestão de diversas Startups da área');

-- Insert mock data into the activities table
INSERT INTO event_manager.activities (event_id, speaker_id, type, name, description, date, start_time, end_time, location)
VALUES
  (1, 1, 'Workshop', 'Workshop 1', 'Descrição do Workshop 1', '2023-11-01', '09:00:00', '11:00:00', 'Galpão da Arquitetura'),
  (1, 2, 'Aula', 'Lecture 1', 'Descrição da Lecture 1', '2023-11-02', '10:00:00', '12:00:00', 'Proway'),
  (2, 3, 'Painel', 'Panel 1', 'Descrição do Panel 1', '2023-12-06', '11:30:00', '13:30:00', 'Auditório');

-- Insert mock data into the messages table
INSERT INTO event_manager.messages (event_id, sender_id, recipient_id, text, date_time)
VALUES
  (1, 1, 2, 'Olá, está gostando do curso?', '2023-11-01 09:15:00'),
  (1, 2, 1, 'Estou sim!', '2023-11-01 09:30:00'),
  (2, 1, 2, 'Como fazer para enviar mensagens diretamente pro palestrante?', '2023-12-06 11:45:00'),
  (2, 2, 1, 'Tenta ir no menu de Perguntas', '2023-12-06 11:46:00');

-- Insert mock data into the questions table
INSERT INTO event_manager.questions (event_id, activity_id, speaker_id, user_id, question_text, approved, excluded)
VALUES
  (1, 1, 1, 1, 'O que vamos aprender nesse evento?', true, false),
  (1, 2, 2, 2, 'Os slides vão ser compartilhados?', true, false),
  (2, 3, 3, 2, 'Que lições mais importantes você aprendeu na sua carreira?', true, false);

-- Insert mock data into the treasures table
INSERT INTO event_manager.treasures (event_id, activity_id, token, score, hidden)
VALUES
  (1, 1, 'token1', 10, false),
  (1, 2, 'token2', 15, true),
  (2, 3, 'token3', 5, true);


-- Insert mock data into the users_events table
INSERT INTO event_manager.users_events (user_id, event_id)
VALUES
  (1, 1),
  (1, 2),
  (2, 1),
  (2, 2);

INSERT INTO event_manager.users_activities (user_id, activity_id)
VALUES
  (1, 1),
  (1, 2),
  (2, 1);

-- Insert mock data into the my_treasures table
INSERT INTO event_manager.users_treasures (user_id, treasure_id)
VALUES
  (1, 1),
  (1, 2),
  (2, 2);
