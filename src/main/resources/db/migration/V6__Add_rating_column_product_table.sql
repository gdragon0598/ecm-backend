ALTER table product drop column rating;
ALTER table product add column rating float check ( rating >= 0 and rating <= 5) default 0;