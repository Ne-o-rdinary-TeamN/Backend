-- hackerthon.tb_news definition

CREATE TABLE `tb_news` (
  `news_pk` bigint NOT NULL AUTO_INCREMENT,
  `news_title` varchar(30) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`news_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hackerthon.tb_user definition

CREATE TABLE `tb_user` (
  `user_pk` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `user_pw` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hackerthon.tb_post definition

CREATE TABLE `tb_post` (
  `post_pk` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `agree` varchar(255) DEFAULT NULL,
  `disagree` varchar(255) DEFAULT NULL,
  `category` enum('ECONOMY','HOT','LOVE','POLITICS','SOCIAL') DEFAULT NULL,
  `agree_count` bigint DEFAULT NULL,
  `disagree_count` bigint DEFAULT NULL,
  `agree_rate` double DEFAULT NULL,
  `disagree_rate` double DEFAULT NULL,
  `user_pk` bigint DEFAULT NULL,
  `comment_count` bigint DEFAULT NULL,
  `total_count` bigint DEFAULT NULL,
  PRIMARY KEY (`post_pk`),
  KEY `FKdndqe9nk3cf55pgopee2swm2u` (`user_pk`),
  CONSTRAINT `FKdndqe9nk3cf55pgopee2swm2u` FOREIGN KEY (`user_pk`) REFERENCES `tb_user` (`user_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hackerthon.tb_recommendation definition

CREATE TABLE `tb_recommendation` (
  `recommendation_pk` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `post_pk` bigint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`recommendation_pk`),
  KEY `FKt4qms4amlpv2ws4uituh2hbn5` (`post_pk`),
  CONSTRAINT `FKt4qms4amlpv2ws4uituh2hbn5` FOREIGN KEY (`post_pk`) REFERENCES `tb_post` (`post_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hackerthon.tb_comment definition

CREATE TABLE `tb_comment` (
  `comment_pk` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `post_pk` bigint DEFAULT NULL,
  `user_pk` bigint DEFAULT NULL,
  `like_count` bigint DEFAULT NULL,  
  PRIMARY KEY (`comment_pk`),
  KEY `FKl2hdhg13bchi8ftcbs1g0t0eo` (`post_pk`),
  KEY `FK5n2j7qhea0nccwcar17gngvia` (`user_pk`),
  CONSTRAINT `FK5n2j7qhea0nccwcar17gngvia` FOREIGN KEY (`user_pk`) REFERENCES `tb_user` (`user_pk`),
  CONSTRAINT `FKl2hdhg13bchi8ftcbs1g0t0eo` FOREIGN KEY (`post_pk`) REFERENCES `tb_post` (`post_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hackerthon.tb_comment_like definition

CREATE TABLE `tb_comment_like` (
  `comment_like_pk` bigint NOT NULL AUTO_INCREMENT,
  `comment_pk` bigint DEFAULT NULL,
  `user_pk` bigint DEFAULT NULL,
  PRIMARY KEY (`comment_like_pk`),
  KEY `FK1arcr4q6i9sh3xoo0853nil2h` (`comment_pk`),
  KEY `FKiidnhll7ndhjvov7999avim70` (`user_pk`),
  CONSTRAINT `FK1arcr4q6i9sh3xoo0853nil2h` FOREIGN KEY (`comment_pk`) REFERENCES `tb_comment` (`comment_pk`),
  CONSTRAINT `FKiidnhll7ndhjvov7999avim70` FOREIGN KEY (`user_pk`) REFERENCES `tb_user` (`user_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hackerthon.tb_hashtag definition

CREATE TABLE `tb_hashtag` (
  `hash_tag_pk` bigint NOT NULL AUTO_INCREMENT,
  `post_pk` bigint DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`hash_tag_pk`),
  KEY `FK13bud29xociv69dlo5elxv13p` (`post_pk`),
  CONSTRAINT `FK13bud29xociv69dlo5elxv13p` FOREIGN KEY (`post_pk`) REFERENCES `tb_post` (`post_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hackerthon.tb_opinion definition

CREATE TABLE `tb_opinion` (
  `opinion_pk` bigint NOT NULL AUTO_INCREMENT,
  `post_pk` bigint DEFAULT NULL,
  `user_pk` bigint DEFAULT NULL,
  `opinion` enum('AGREE','DISAGREE') DEFAULT NULL,
  PRIMARY KEY (`opinion_pk`),
  KEY `FKslapf70j8rhx61731wppwhj2i` (`post_pk`),
  KEY `FKket1v46g80m2x0pfrtkh8abi` (`user_pk`),
  CONSTRAINT `FKket1v46g80m2x0pfrtkh8abi` FOREIGN KEY (`user_pk`) REFERENCES `tb_user` (`user_pk`),
  CONSTRAINT `FKslapf70j8rhx61731wppwhj2i` FOREIGN KEY (`post_pk`) REFERENCES `tb_post` (`post_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
