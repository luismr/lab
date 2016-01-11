create database knowhub;
grant all on knowhub.* to knowhub@'%' identified by 'knowhub-passwd';
grant all on knowhub.* to knowhub@localhost identified by 'knowhub-passwd';

use knowhub;
