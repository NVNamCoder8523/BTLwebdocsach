-- Active: 1699781558278@@127.0.0.1@3306@webdocsach

CREATE DATABASE webdocsach
    DEFAULT CHARACTER SET = 'utf8mb4';
    
INSERT INTO webdocsach.book (title, author, release_date, upload_at, cover_image, description)
VALUES ('Những người khốn khổ', 'Victor Hugo', '2007-10-05', '2023-10-10', 'nhung-nguoi-khon-kho.png', NULL),
         ('Nhà giả kim', 'Paulo Coelho', '2010-10-08', '2023-10-10', 'nha-gia-kim.png', NULL),
         ('Đời ngắn đừng ngủ dài', 'Robin Sharma', '2012-10-10', '2023-11-10', 'default.png', NULL),
         ('Đắc nhân tâm', 'Dale Carnegie', '2010-10-05', '2023-10-10', 'dac-nhan-tam.jpg', 'Top sách bán chạy nhất 2022'),
         ('Code dạo kí sự', 'Phạm Huy Hoàng', '2007-06-21', '2023-10-10', 'code-dao-ki-su.png', NULL),
         ('Đại tư tế', 'Hoa Ngọc Lan', '2011-06-21', '2023-10-10', 'default.png', 'Truyện cổ kinh dị, đừng xem');
         