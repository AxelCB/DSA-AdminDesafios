--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: dsa; Type: DATABASE; Schema: -; Owner: dsa
--

ALTER DATABASE dsa OWNER TO dsa;

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: dsa
--

CREATE TABLE admin (
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255)
);


ALTER TABLE admin OWNER TO dsa;

--
-- Name: admin_id_seq; Type: SEQUENCE; Schema: public; Owner: dsa
--

CREATE SEQUENCE admin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE admin_id_seq OWNER TO dsa;

--
-- Name: category; Type: TABLE; Schema: public; Owner: dsa
--

CREATE TABLE category (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE category OWNER TO dsa;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: dsa
--

CREATE SEQUENCE category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE category_id_seq OWNER TO dsa;

--
-- Name: challenge; Type: TABLE; Schema: public; Owner: dsa
--

CREATE TABLE challenge (
    id bigint NOT NULL,
    answer_description character varying(255),
    attached_file_url character varying(255),
    description character varying(255),
    points bigint,
    title character varying(255),
    valid_answer character varying(255),
    category_id bigint,
    hint1_id bigint,
    hint2_id bigint,
    next_challenge_id bigint
);


ALTER TABLE challenge OWNER TO dsa;

--
-- Name: challenge_id_seq; Type: SEQUENCE; Schema: public; Owner: dsa
--

CREATE SEQUENCE challenge_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE challenge_id_seq OWNER TO dsa;

--
-- Name: configuration; Type: TABLE; Schema: public; Owner: dsa
--

CREATE TABLE configuration (
    id bigint NOT NULL,
    name character varying(255),
    value character varying(255)
);


ALTER TABLE configuration OWNER TO dsa;

--
-- Name: configuration_id_seq; Type: SEQUENCE; Schema: public; Owner: dsa
--

CREATE SEQUENCE configuration_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE configuration_id_seq OWNER TO dsa;

--
-- Name: hint; Type: TABLE; Schema: public; Owner: dsa
--

CREATE TABLE hint (
    id bigint NOT NULL,
    description character varying(255),
    points_percentage_cost bigint
);


ALTER TABLE hint OWNER TO dsa;

--
-- Name: hint_id_seq; Type: SEQUENCE; Schema: public; Owner: dsa
--

CREATE SEQUENCE hint_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hint_id_seq OWNER TO dsa;

--
-- Name: player; Type: TABLE; Schema: public; Owner: dsa
--

CREATE TABLE player (
    id bigint NOT NULL,
    player_id bigint,
    username character varying(255),
    team_id bigint
);


ALTER TABLE player OWNER TO dsa;

--
-- Name: player_id_seq; Type: SEQUENCE; Schema: public; Owner: dsa
--

CREATE SEQUENCE player_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE player_id_seq OWNER TO dsa;

--
-- Name: solved_challenge; Type: TABLE; Schema: public; Owner: dsa
--

CREATE TABLE solved_challenge (
    id bigint NOT NULL,
    obtained_score bigint,
    challenge_id bigint,
    solver_id bigint
);


ALTER TABLE solved_challenge OWNER TO dsa;

--
-- Name: solved_challenge_id_seq; Type: SEQUENCE; Schema: public; Owner: dsa
--

CREATE SEQUENCE solved_challenge_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE solved_challenge_id_seq OWNER TO dsa;

--
-- Name: team; Type: TABLE; Schema: public; Owner: dsa
--

CREATE TABLE team (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    team_id bigint
);


ALTER TABLE team OWNER TO dsa;

--
-- Name: team_id_seq; Type: SEQUENCE; Schema: public; Owner: dsa
--

CREATE SEQUENCE team_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE team_id_seq OWNER TO dsa;

--
-- Name: team_solved_challenges; Type: TABLE; Schema: public; Owner: dsa
--

CREATE TABLE team_solved_challenges (
    team_id bigint NOT NULL,
    solved_challenges_id bigint NOT NULL
);


ALTER TABLE team_solved_challenges OWNER TO dsa;

--
-- Name: team_used_hints; Type: TABLE; Schema: public; Owner: dsa
--

CREATE TABLE team_used_hints (
    team_id bigint NOT NULL,
    used_hints_id bigint NOT NULL
);


ALTER TABLE team_used_hints OWNER TO dsa;

--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: dsa
--

INSERT INTO admin VALUES (1, '$2a$12$kz.RD0tCjNNmT/oP3AEW9ezHmZZrJIGZPKhHyE/8f5dTvoL7SGBgC', 'admin');


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: dsa
--

INSERT INTO category VALUES (1, 'forensia');
INSERT INTO category VALUES (2, 'inyección');
INSERT INTO category VALUES (3, 'criptografía');


--
-- Data for Name: challenge; Type: TABLE DATA; Schema: public; Owner: dsa
--

INSERT INTO challenge VALUES (1, 'para evitar esto no hay que tomar input', NULL, 'trata de encontrar el flag escondido en http://asd.qwe/index.php?desafio=2', 60, 'inyeccion 1', '12222_lalala', 2, 1, NULL, NULL);
INSERT INTO challenge VALUES (2, 'viste???', NULL, 'trata de encontrar el flag escondido en http://asd.qwe/index.php?desafio=2', 25, 'inyeccion 2', '12356_lalala', 2, 2, NULL, NULL);


--
-- Data for Name: configuration; Type: TABLE DATA; Schema: public; Owner: dsa
--

INSERT INTO configuration VALUES (1, 'id_juego', '?');
INSERT INTO configuration VALUES (2, 'progressive', 'true');


--
-- Data for Name: hint; Type: TABLE DATA; Schema: public; Owner: dsa
--

INSERT INTO hint VALUES (1, 'buscalo en google', 10);
INSERT INTO hint VALUES (2, 'busca debajo de la tapa del piano', 25);


--
-- Data for Name: player; Type: TABLE DATA; Schema: public; Owner: dsa
--

INSERT INTO player VALUES (1, NULL, 'pedro', 1);
INSERT INTO player VALUES (2, NULL, 'juan22', 1);
INSERT INTO player VALUES (3, NULL, 'pepe', 1);
INSERT INTO player VALUES (4, NULL, 'luisa', 2);
INSERT INTO player VALUES (5, NULL, 'hector', 3);
INSERT INTO player VALUES (6, NULL, 'twisted_cables', 3);
INSERT INTO player VALUES (7, NULL, 'xxxHungarOxxx', 1);


--
-- Data for Name: solved_challenge; Type: TABLE DATA; Schema: public; Owner: dsa
--

INSERT INTO solved_challenge VALUES (1, 25, 2, 2);


--
-- Data for Name: team; Type: TABLE DATA; Schema: public; Owner: dsa
--

INSERT INTO team VALUES (1, 'team 1', NULL);
INSERT INTO team VALUES (2, 'team 2', NULL);
INSERT INTO team VALUES (3, 'team 3', NULL);


--
-- Data for Name: team_solved_challenges; Type: TABLE DATA; Schema: public; Owner: dsa
--

INSERT INTO team_solved_challenges VALUES (1, 1);


--
-- Data for Name: team_used_hints; Type: TABLE DATA; Schema: public; Owner: dsa
--



--
-- Name: admin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dsa
--

SELECT pg_catalog.setval('admin_id_seq', 1, false);


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dsa
--

SELECT pg_catalog.setval('category_id_seq', 4, false);


--
-- Name: challenge_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dsa
--

SELECT pg_catalog.setval('challenge_id_seq', 3, false);


--
-- Name: configuration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dsa
--

SELECT pg_catalog.setval('configuration_id_seq', 3, false);


--
-- Name: hint_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dsa
--

SELECT pg_catalog.setval('hint_id_seq', 3, false);


--
-- Name: player_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dsa
--

SELECT pg_catalog.setval('player_id_seq', 3, false);


--
-- Name: solved_challenge_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dsa
--

SELECT pg_catalog.setval('solved_challenge_id_seq', 3, false);


--
-- Name: team_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dsa
--

SELECT pg_catalog.setval('team_id_seq', 4, false);


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: challenge challenge_pkey; Type: CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY challenge
    ADD CONSTRAINT challenge_pkey PRIMARY KEY (id);


--
-- Name: configuration configuration_pkey; Type: CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY configuration
    ADD CONSTRAINT configuration_pkey PRIMARY KEY (id);


--
-- Name: hint hint_pkey; Type: CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY hint
    ADD CONSTRAINT hint_pkey PRIMARY KEY (id);


--
-- Name: player player_pkey; Type: CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY player
    ADD CONSTRAINT player_pkey PRIMARY KEY (id);


--
-- Name: solved_challenge solved_challenge_pkey; Type: CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY solved_challenge
    ADD CONSTRAINT solved_challenge_pkey PRIMARY KEY (id);


--
-- Name: team team_pkey; Type: CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY team
    ADD CONSTRAINT team_pkey PRIMARY KEY (id);


--
-- Name: admin uk_gfn44sntic2k93auag97juyij; Type: CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY admin
    ADD CONSTRAINT uk_gfn44sntic2k93auag97juyij UNIQUE (username);


--
-- Name: challenge fk5qkg2luursh3f6b4cq0ufu9m2; Type: FK CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY challenge
    ADD CONSTRAINT fk5qkg2luursh3f6b4cq0ufu9m2 FOREIGN KEY (hint2_id) REFERENCES hint(id);


--
-- Name: solved_challenge fk9uc41h34wrq1nu23yobcs69wa; Type: FK CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY solved_challenge
    ADD CONSTRAINT fk9uc41h34wrq1nu23yobcs69wa FOREIGN KEY (challenge_id) REFERENCES challenge(id);


--
-- Name: team_used_hints fkc4ocv5sg476gxpdlcbbb7uxlp; Type: FK CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY team_used_hints
    ADD CONSTRAINT fkc4ocv5sg476gxpdlcbbb7uxlp FOREIGN KEY (team_id) REFERENCES team(id);


--
-- Name: player fkdvd6ljes11r44igawmpm1mc5s; Type: FK CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY player
    ADD CONSTRAINT fkdvd6ljes11r44igawmpm1mc5s FOREIGN KEY (team_id) REFERENCES team(id);


--
-- Name: team_solved_challenges fkf6t57lxr83p021q5ktc2rnt2s; Type: FK CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY team_solved_challenges
    ADD CONSTRAINT fkf6t57lxr83p021q5ktc2rnt2s FOREIGN KEY (team_id) REFERENCES team(id);


--
-- Name: solved_challenge fkgejib83wi4a6nwxufoijx5w3a; Type: FK CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY solved_challenge
    ADD CONSTRAINT fkgejib83wi4a6nwxufoijx5w3a FOREIGN KEY (solver_id) REFERENCES player(id);


--
-- Name: challenge fkh6otfb3o9wvmgf946v4w5leux; Type: FK CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY challenge
    ADD CONSTRAINT fkh6otfb3o9wvmgf946v4w5leux FOREIGN KEY (hint1_id) REFERENCES hint(id);


--
-- Name: team_solved_challenges fkm4ock3wl8jcb7jwgcp1d7l9wj; Type: FK CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY team_solved_challenges
    ADD CONSTRAINT fkm4ock3wl8jcb7jwgcp1d7l9wj FOREIGN KEY (solved_challenges_id) REFERENCES solved_challenge(id);


--
-- Name: challenge fkost843eox9kmcjgbofxd0jk8k; Type: FK CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY challenge
    ADD CONSTRAINT fkost843eox9kmcjgbofxd0jk8k FOREIGN KEY (next_challenge_id) REFERENCES challenge(id);


--
-- Name: challenge fkq6nt2ygcs2o9f06tb8tr42toy; Type: FK CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY challenge
    ADD CONSTRAINT fkq6nt2ygcs2o9f06tb8tr42toy FOREIGN KEY (category_id) REFERENCES category(id);


--
-- Name: team_used_hints fkrsnfxc03ccfe5xa6a5m1nlct7; Type: FK CONSTRAINT; Schema: public; Owner: dsa
--

ALTER TABLE ONLY team_used_hints
    ADD CONSTRAINT fkrsnfxc03ccfe5xa6a5m1nlct7 FOREIGN KEY (used_hints_id) REFERENCES hint(id);


--
-- PostgreSQL database dump complete
--

