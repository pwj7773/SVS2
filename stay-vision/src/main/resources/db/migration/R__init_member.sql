INSERT INTO svs.MEMBER(       
					USER_ID,	       
					USER_PW,        
					USER_NAME,    
					EMAIL, 
					PHONE,        
					ROLENAME,	    
					status)
  VALUES (
				'admin', 
				'{bcrypt}$2a$10$cbdnb4FRynrvG73gb7R2/Oo1wCXHGrob3XXApahP5RnbgKt9TgZou',
				'관리자',
				'admin@admin.com',
				'010-0000-0000',
				'ROLE_ADMIN',
				'ACTIVE')
   
   
  