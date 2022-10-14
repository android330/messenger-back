INSERT INTO AUTH(USERNAME, PASSWORD ) VALUES
('asdf', '$2a$10$YAFEo45urLYI23RH9rMibOUo5fDmEU3Rw6nPxDMyyA3gHRuhFP.cO'),
-- p = password
('Ham', '$2a$10$juNTiBeWXw0qiVIGnt5oN.5FlLEY.iEv.MOeJ/uATdC9gt5h5w.Qi');
-- p = test

INSERT INTO USER_POSTER(USERNAME, PICTURE) VALUES
('asdf', 'https://i.ytimg.com/vi/JQ1txLdu6qg/maxresdefault.jpg'),
('The drive', 'https://images.pexels.com/photos/247502/pexels-photo-247502.jpeg?cs=srgb&dl=pexels-pixabay-247502.jpg'),
('Ham','https://images.ctfassets.net/hrltx12pl8hq/5qusjAtcAaetdPsing4jR6/2db2f75abd2840caa09d21312b4fc6c8/animals-wildlife-shutterstock_1066200380.jpg'),
('Test123', 'https://i.natgeofe.com/k/c02b35d2-bfd7-4ed9-aad4-8e25627cd481/komodo-dragon-head-on_2x1.jpg'),
('xX_Siper420_Xx','https://www.iata.org/contentassets/d7c512eb9a704ba2a8056e3186a31921/cargo_live_animals_parrot.jpg'),
('Very long username man', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwallpaperset.com%2Fw%2Ffull%2Fe%2F1%2F1%2F180487.jpg');

INSERT INTO POST(USER_ID, MESSAGE, PICTURE, DATE) VALUES
(1, 'Hey how it going', NULL, '2022-10-12 12:30:00'),
(2, 'Good, u?', NULL, '2022-10-12 12:30:00'),
(1, 'Im swell!!!', NULL, '2022-10-12 12:30:00'),
(2, 'Swell?', NULL, '2022-10-12 12:30:00'),
(3, 'He means swoll lol, hit the gym probably', NULL, '2022-10-12 12:30:00'),
(2, 'lol', NULL, '2022-10-12 12:30:00'),
(1, 'u suck', NULL, '2022-10-12 12:30:00'),
(2, 'hahahahahahahah XD', NULL, '2022-10-12 12:30:00'),
(1, 'u guys doing anthing later', NULL, '2022-10-12 12:30:00'),
(2, 'nah', NULL, '2022-10-12 12:30:00'),
(2, 'U guys got plans', NULL, '2022-10-12 12:30:00'),
(3, 'Come to my house at 10 for some beers and a movies', NULL, '2022-10-12 12:30:00');