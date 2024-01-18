CREATE TABLE rent(
    id int auto_increment primary key,
    customer_id int not null,
    nfe varchar(255),
    price DECIMAL(15,2) not null,
    created_at DATETIME not null,
        FOREIGN KEY (customer_id) REFERENCES customer(id)
);
CREATE TABLE rent_room(
    rent_id int not null,
    room_id int not null,
    FOREIGN KEY (rent_id) REFERENCES rent(id),
    FOREIGN KEY (room_id) REFERENCES room(id),
    PRIMARY KEY(rent_id, room_id)

  );
