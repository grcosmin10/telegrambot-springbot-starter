INSERT INTO commonmessage(id,language,message,condition)  VALUES('1','RO','Salut eu sunt IMOBbot, expertul tau in impobiliare, cu ce te pot ajuta ?','start') ON CONFLICT DO NOTHING;
INSERT INTO commonmessage(id,language,message,condition)  VALUES('2','RO','Mesaj Test 2','condition2') ON CONFLICT DO NOTHING;
INSERT INTO commonmessage(id,language,message,condition)  VALUES('3','RO','Mesaj Test 3','condition3') ON CONFLICT DO NOTHING;
INSERT INTO commonmessage(id,language,message,condition)  VALUES('4','RO','Mesaj Test 4','condition4') ON CONFLICT DO NOTHING;
INSERT INTO commonmessage(id,language,message,condition)  VALUES('5','RO','Mesaj Test 5','condition5') ON CONFLICT DO NOTHING;
