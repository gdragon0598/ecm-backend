CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,
                        email VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        first_name VARCHAR(50) NOT NULL,
                        last_name VARCHAR(50) NOT NULL,
                        sex CHAR(1) NOT NULL,
                        date_of_birth DATE NOT NULL,
                        created_at TIMESTAMP DEFAULT NOW(),
                        updated_at TIMESTAMP,
                        created_by INTEGER,
                        updated_by INTEGER

);

CREATE TABLE role (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      created_at TIMESTAMP DEFAULT NOW(),
                      updated_at TIMESTAMP,
                      created_by INTEGER,
                      updated_by INTEGER
);

CREATE TABLE user_role (
                           user_id INTEGER REFERENCES "user"(id),
                           role_id INTEGER REFERENCES role(id),
                           PRIMARY KEY (user_id, role_id),
                           created_at TIMESTAMP DEFAULT NOW(),
                           updated_at TIMESTAMP,
                           created_by INTEGER,
                           updated_by INTEGER
);

CREATE TABLE access_group (
                              id SERIAL PRIMARY KEY,
                              name VARCHAR(100) NOT NULL,
                              role_id INTEGER NOT NULL REFERENCES role(id),
                              privilege VARCHAR(50) NOT NULL,
                              CONSTRAINT fk_access_group_role FOREIGN KEY (role_id) REFERENCES role(id),
                              created_at TIMESTAMP DEFAULT NOW(),
                              updated_at TIMESTAMP,
                              created_by INTEGER,
                              updated_by INTEGER
);

CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description TEXT,
                          created_at TIMESTAMP DEFAULT NOW(),
                          updated_at TIMESTAMP,
                          created_by INTEGER,
                          updated_by INTEGER

);

CREATE TABLE brand (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       description TEXT,
                       created_at TIMESTAMP DEFAULT NOW(),
                       updated_at TIMESTAMP,
                       created_by INTEGER,
                       updated_by INTEGER
);

CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         category_id INTEGER NOT NULL,
                         brand_id INTEGER NOT NULL,
                         supplier_id INTEGER,
                         image_url VARCHAR(255),
                         sale INTEGER NOT NULL CHECK (sale >=0 AND sale <= 100) ,
                         created_at TIMESTAMP DEFAULT NOW(),
                         updated_at TIMESTAMP,
                         created_by INTEGER,
                         updated_by INTEGER,
                         CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(id),
                         CONSTRAINT fk_product_brand FOREIGN KEY (brand_id) REFERENCES brand(id)
);
ALTER TABLE product ALTER sale SET DEFAULT 0;
ALTER TABLE product ADD is_new BOOLEAN;
ALTER TABLE product ADD number_sold_item INTEGER DEFAULT 0;
ALTER TABLE product ADD total INTEGER DEFAULT 0;
CREATE TABLE product_detail (
                                id SERIAL PRIMARY KEY,
                                product_id INTEGER NOT NULL REFERENCES product(id),
                                description TEXT,
                                additional_information TEXT,
                                created_at TIMESTAMP DEFAULT NOW(),
                                updated_at TIMESTAMP,
                                created_by INTEGER,
                                updated_by INTEGER
);
ALTER TABLE product_detail ADD CONSTRAINT Uidx_ProductID UNIQUE(product_id);

CREATE TABLE product_image (
                               id SERIAL PRIMARY KEY,
                               product_detail_id INTEGER NOT NULL REFERENCES product_detail(id),
                               image_url VARCHAR(255) NOT NULL,
                               created_at TIMESTAMP DEFAULT NOW(),
                               updated_at TIMESTAMP,
                               created_by INTEGER,
                               updated_by INTEGER
);

CREATE TABLE comment (
                         id SERIAL PRIMARY KEY,
                         product_id INTEGER NOT NULL REFERENCES product(id),
                         user_id INTEGER NOT NULL REFERENCES "user"(id),
                         comment_text TEXT,
                         rating INTEGER CHECK (rating >= 0 AND rating <= 5),
                         created_at TIMESTAMP DEFAULT NOW(),
                         updated_at TIMESTAMP,
                         created_by INTEGER,
                         updated_by INTEGER
);
ALTER TABLE comment ADD CONSTRAINT Uidx_ProductID_UserID UNIQUE(product_id, user_id);

CREATE TABLE "order" (
                         id SERIAL PRIMARY KEY,
                         user_id INTEGER NOT NULL REFERENCES "user"(id),
                         order_date DATE NOT NULL,
                         total_amount DECIMAL(10, 2) NOT NULL,
                         status VARCHAR(20) NOT NULL,
                         created_at TIMESTAMP DEFAULT NOW(),
                         updated_at TIMESTAMP,
                         created_by INTEGER,
                         updated_by INTEGER
);

CREATE TABLE order_detail (
                              id SERIAL PRIMARY KEY,
                              order_id INTEGER NOT NULL REFERENCES "order"(id),
                              product_id INTEGER NOT NULL REFERENCES product(id),
                              quantity INTEGER NOT NULL,
                              price DECIMAL(10, 2) NOT NULL,
                              created_at TIMESTAMP DEFAULT NOW(),
                              updated_at TIMESTAMP,
                              created_by INTEGER,
                              updated_by INTEGER
);
ALTER TABLE order_detail ADD CONSTRAINT Uidx_OrderID_ProductID UNIQUE (order_id, product_id);

CREATE TABLE address (
                         id SERIAL PRIMARY KEY,
                         street VARCHAR(255) NOT NULL,
                         city VARCHAR(100) NOT NULL,
                         province VARCHAR(100) NOT NULL,
                         postal_code VARCHAR(20) NOT NULL,
                         created_at TIMESTAMP DEFAULT NOW(),
                         updated_at TIMESTAMP,
                         created_by INTEGER,
                         updated_by INTEGER
);

CREATE TABLE supplier (
                          user_id INTEGER PRIMARY KEY REFERENCES "user"(id),
                          company_name VARCHAR(100) NOT NULL,
                          address_id INTEGER REFERENCES address(id),
                          contact_number VARCHAR(20),
                          description TEXT,
                          created_at TIMESTAMP DEFAULT NOW(),
                          updated_at TIMESTAMP,
                          created_by INTEGER,
                          updated_by INTEGER
);
ALTER TABLE supplier DROP address_id;
CREATE TABLE customer (
                          user_id INTEGER PRIMARY KEY REFERENCES "user"(id),
                          address_id INTEGER REFERENCES address(id),
                          created_at TIMESTAMP DEFAULT NOW(),
                          updated_at TIMESTAMP,
                          created_by INTEGER,
                          updated_by INTEGER
);
ALTER TABLE customer DROP address_id;
ALTER TABLE customer ADD number_order INTEGER DEFAULT 0;
ALTER TABLE "user" ADD CONSTRAINT unique_email UNIQUE (email);
ALTER TABLE "user" ADD address_id INTEGER REFERENCES address(id);
