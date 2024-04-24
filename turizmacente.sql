--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: feature; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.feature (
    feature_id integer NOT NULL,
    feature_name text NOT NULL
);


ALTER TABLE public.feature OWNER TO postgres;

--
-- Name: feature_feature_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.feature ALTER COLUMN feature_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.feature_feature_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: otel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.otel (
    otel_id integer NOT NULL,
    otel_name text NOT NULL,
    otel_address text NOT NULL,
    otel_mail text,
    otel_phoneno text,
    otel_star integer,
    otel_pensiontype_id integer,
    otel_feature_ids integer[],
    otel_room_id integer
);


ALTER TABLE public.otel OWNER TO postgres;

--
-- Name: otel_otel_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.otel ALTER COLUMN otel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.otel_otel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: pension; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pension (
    pensiontype_id integer NOT NULL,
    pensiontype_name text NOT NULL
);


ALTER TABLE public.pension OWNER TO postgres;

--
-- Name: pension_pensiontype_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.pension ALTER COLUMN pensiontype_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pensiontype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    reservation_customer_name text NOT NULL,
    reservation_otel_id integer NOT NULL,
    reservation_strt_date date NOT NULL,
    reservation_fnsh_date date NOT NULL,
    reservation_adult_number integer NOT NULL,
    reservation_child_number integer,
    reservation_total_price bigint
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- Name: reservation_reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: room; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.room (
    room_id integer NOT NULL,
    room_name text NOT NULL,
    room_price bigint NOT NULL
);


ALTER TABLE public.room OWNER TO postgres;

--
-- Name: room_room_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: season; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.season (
    season_id integer NOT NULL,
    season_name text NOT NULL,
    season_strt_date date NOT NULL,
    season_fnsh_date date NOT NULL,
    season_rate_multiplier numeric
);


ALTER TABLE public.season OWNER TO postgres;

--
-- Name: season_season_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL,
    user_fullname text NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: user_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: feature; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.feature (feature_id, feature_name) FROM stdin;
1	Ücretsiz Otopark
3	Yüzme Havuzu
4	Hotel Concierge
6	7/24 Oda Servisi
2	Ücretsiz Wi-Fi
5	SPA
\.


--
-- Data for Name: otel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.otel (otel_id, otel_name, otel_address, otel_mail, otel_phoneno, otel_star, otel_pensiontype_id, otel_feature_ids, otel_room_id) FROM stdin;
2	JW Marriot Ankara	Ankara	marriot@gmail.com	123123123	5	1	{1,2,3,4}	2
4	Çırağan Palace	İstanbul	ciragan@gmail.com	12341234	5	1	{1,2,3,4,5,6,7,8}	3
\.


--
-- Data for Name: pension; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pension (pensiontype_id, pensiontype_name) FROM stdin;
1	Ultra Her şey Dahil
2	Her şey Dahil\n
3	Oda Kahvaltı
4	Tam Pansiyon
5	Yarım Pansiyon
6	Sadece Yatak
7	Alkol Hariç Full Credit
\.


--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservation (reservation_id, reservation_customer_name, reservation_otel_id, reservation_strt_date, reservation_fnsh_date, reservation_adult_number, reservation_child_number, reservation_total_price) FROM stdin;
1	Ulaş Gültekin	2	2024-04-24	2024-04-26	2	1	0
\.


--
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.room (room_id, room_name, room_price) FROM stdin;
1	Tek Yataklı Oda	500
2	Çift Yataklı Oda	800
3	2 Kişilik Ranza	350
\.


--
-- Data for Name: season; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.season (season_id, season_name, season_strt_date, season_fnsh_date, season_rate_multiplier) FROM stdin;
2	Yaz Sezonu	2024-06-01	2024-09-30	1.2
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (user_id, user_name, user_password, user_role, user_fullname) FROM stdin;
4	gsonmez	12345	admin	Gürkan Sönmez
5	esayın	12345	employee	Emine Nur Sayın
8	mehmet	12345	admin	Mehmet Canpolat
\.


--
-- Name: feature_feature_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.feature_feature_id_seq', 7, true);


--
-- Name: otel_otel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.otel_otel_id_seq', 4, true);


--
-- Name: pension_pensiontype_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pension_pensiontype_id_seq', 8, true);


--
-- Name: reservation_reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 1, true);


--
-- Name: room_room_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.room_room_id_seq', 3, true);


--
-- Name: season_season_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.season_season_id_seq', 2, true);


--
-- Name: user_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_user_id_seq', 9, true);


--
-- Name: feature feature_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feature
    ADD CONSTRAINT feature_pkey PRIMARY KEY (feature_id);


--
-- Name: otel otel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.otel
    ADD CONSTRAINT otel_pkey PRIMARY KEY (otel_id);


--
-- Name: pension pension_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pensiontype_id);


--
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);


--
-- Name: room room_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);


--
-- Name: season season_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- PostgreSQL database dump complete
--

