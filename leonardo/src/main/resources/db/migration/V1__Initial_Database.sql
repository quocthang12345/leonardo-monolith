-- 1. Independent Entities
CREATE TABLE IF NOT EXISTS cart (
    id BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255) NOT NULL,
    updated_by VARCHAR(255),
    item_name VARCHAR(255),
    count BIGINT,
    type VARCHAR(255)
 );

CREATE TABLE IF NOT EXISTS chat_messages (
    id BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255) NOT NULL,
    updated_by VARCHAR(255),
    type VARCHAR(50) NOT NULL,
    content TEXT,
    sender VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS list_journey (
    id BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255) NOT NULL,
    updated_by VARCHAR(255),
    title VARCHAR(255),
    img_journey VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS fashion (
    id BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255) NOT NULL,
    updated_by VARCHAR(255),
    img_display VARCHAR(255),
    img_continue VARCHAR(255),
    name VARCHAR(255),
    another_name VARCHAR(255),
    type VARCHAR(255),
    model VARCHAR(255),
    price VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS menu_men (
    id BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255) NOT NULL,
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS menu_women (
    id BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255) NOT NULL,
    updated_by VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS roles (
    id BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255) NOT NULL,
    updated_by VARCHAR(255),
    role_name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    is_enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255) NOT NULL,
    updated_by VARCHAR(255),
    username VARCHAR(255) NOT NULL UNIQUE,
    fullname VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255),
    image_url VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    status INTEGER,
    email_verified BOOLEAN DEFAULT FALSE,
    verify_code VARCHAR(255),
    provider VARCHAR(50) NOT NULL,
    provider_id VARCHAR(255)
);

-- 2. Child/Related Entities (Tables with Foreign Keys)
CREATE TABLE IF NOT EXISTS type_colors (
    id BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255) NOT NULL,
    updated_by VARCHAR(255),
    color VARCHAR(255),
    img_color VARCHAR(255),
    fashion_id BIGINT REFERENCES fashion(id)
);

CREATE TABLE IF NOT EXISTS menus (
    id BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255) NOT NULL,
    updated_by VARCHAR(255),
    men_menu_id BIGINT UNIQUE REFERENCES menu_men(id),
    women_menu_id BIGINT UNIQUE REFERENCES menu_women(id)
);

-- 3. Element Collections (Simple String Lists)
CREATE TABLE IF NOT EXISTS fashion_images (
    fashion_id BIGINT NOT NULL REFERENCES fashion(id),
    image_url VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS journey_descriptions (
    journey_id BIGINT NOT NULL REFERENCES list_journey(id),
    description VARCHAR(255)
);

-- Collection Tables for Men's Menu
CREATE TABLE IF NOT EXISTS men_wallets (menu_id BIGINT REFERENCES menu_men(id), wallet VARCHAR(255));
CREATE TABLE IF NOT EXISTS men_shoes (menu_id BIGINT REFERENCES menu_men(id), shoes VARCHAR(255));
CREATE TABLE IF NOT EXISTS men_bags (menu_id BIGINT REFERENCES menu_men(id), bag VARCHAR(255));
CREATE TABLE IF NOT EXISTS men_accessories (menu_id BIGINT REFERENCES menu_men(id), accessories VARCHAR(255));

-- Collection Tables for Women's Menu
CREATE TABLE IF NOT EXISTS women_shirts (menu_id BIGINT REFERENCES menu_women(id), shirts VARCHAR(255));
CREATE TABLE IF NOT EXISTS women_trousers (menu_id BIGINT REFERENCES menu_women(id), trousers VARCHAR(255));
CREATE TABLE IF NOT EXISTS women_bags (menu_id BIGINT REFERENCES menu_women(id), bag VARCHAR(255));
CREATE TABLE IF NOT EXISTS women_accessories (menu_id BIGINT REFERENCES menu_women(id), accessories VARCHAR(255));

-- 4. Many-to-Many Join Tables
CREATE TABLE IF NOT EXISTS user_roles (user_id BIGINT NOT NULL REFERENCES users(id),
    role_id BIGINT NOT NULL REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);