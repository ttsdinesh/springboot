ALTER TABLE payments PARTITION BY RANGE (TO_DAYS(created_at))(PARTITION p0 VALUES LESS THAN (TO_DAYS ('2013-01-01')),PARTITION p1 VALUES LESS THAN (TO_DAYS ('2013-02-01')),PARTITION p2 VALUES LESS THAN (TO_DAYS ('2013-03-01')),PARTITION p3 VALUES LESS THAN (TO_DAYS ('2013-04-01')),PARTITION p4 VALUES LESS THAN (TO_DAYS ('2013-05-01')),PARTITION p5 VALUES LESS THAN (TO_DAYS ('2013-06-01')),PARTITION p6 VALUES LESS THAN (TO_DAYS ('2013-07-01')),PARTITION p7 VALUES LESS THAN (TO_DAYS ('2013-08-01')),PARTITION p8 VALUES LESS THAN (TO_DAYS ('2013-09-01')),PARTITION p9 VALUES LESS THAN (TO_DAYS ('2013-10-01')),PARTITION p10 VALUES LESS THAN (TO_DAYS ('2013-11-01')),PARTITION p11 VALUES LESS THAN (TO_DAYS ('2013-12-01')),PARTITION p12 VALUES LESS THAN (TO_DAYS ('2014-01-01')),PARTITION p13 VALUES LESS THAN (TO_DAYS ('2014-02-01')),PARTITION p14 VALUES LESS THAN (TO_DAYS ('2014-03-01')),PARTITION p15 VALUES LESS THAN (TO_DAYS ('2014-04-01')),PARTITION p16 VALUES LESS THAN (TO_DAYS ('2014-05-01')),PARTITION p17 VALUES LESS THAN (TO_DAYS ('2014-06-01')),PARTITION p18 VALUES LESS THAN (TO_DAYS ('2014-07-01')),PARTITION p19 VALUES LESS THAN (TO_DAYS ('2014-08-01')),PARTITION p20 VALUES LESS THAN (TO_DAYS ('2014-09-01')),PARTITION p21 VALUES LESS THAN (TO_DAYS ('2014-10-01')),PARTITION p22 VALUES LESS THAN (TO_DAYS ('2014-11-01')),PARTITION p23 VALUES LESS THAN (TO_DAYS ('2014-12-01')),PARTITION p24 VALUES LESS THAN (TO_DAYS ('2015-01-01')),PARTITION p25 VALUES LESS THAN (TO_DAYS ('2015-02-01')),PARTITION p26 VALUES LESS THAN (TO_DAYS ('2015-03-01')),PARTITION p27 VALUES LESS THAN (TO_DAYS ('2015-04-01')),PARTITION p28 VALUES LESS THAN (TO_DAYS ('2015-05-01')),PARTITION p29 VALUES LESS THAN (TO_DAYS ('2015-06-01')),PARTITION p30 VALUES LESS THAN (TO_DAYS ('2015-07-01')),PARTITION p31 VALUES LESS THAN (TO_DAYS ('2015-08-01')),PARTITION p32 VALUES LESS THAN (TO_DAYS ('2015-09-01')),PARTITION p33 VALUES LESS THAN (TO_DAYS ('2015-10-01')),PARTITION p34 VALUES LESS THAN (TO_DAYS ('2015-11-01')),PARTITION p35 VALUES LESS THAN (TO_DAYS ('2015-12-01')),PARTITION p36 VALUES LESS THAN (TO_DAYS ('2016-01-01')),PARTITION p37 VALUES LESS THAN (TO_DAYS ('2016-02-01')),PARTITION p38 VALUES LESS THAN (TO_DAYS ('2016-03-01')),PARTITION p39 VALUES LESS THAN (TO_DAYS ('2016-04-01')),PARTITION p40 VALUES LESS THAN (TO_DAYS ('2016-05-01')),PARTITION p41 VALUES LESS THAN (TO_DAYS ('2016-06-01')),PARTITION p42 VALUES LESS THAN (TO_DAYS ('2016-07-01')),PARTITION p43 VALUES LESS THAN (TO_DAYS ('2016-08-01')),PARTITION p44 VALUES LESS THAN (TO_DAYS ('2016-09-01')),PARTITION p45 VALUES LESS THAN (TO_DAYS ('2016-10-01')),PARTITION p46 VALUES LESS THAN (TO_DAYS ('2016-11-01')),PARTITION p47 VALUES LESS THAN (TO_DAYS ('2016-12-01')),PARTITION p48 VALUES LESS THAN (TO_DAYS ('2017-01-01')),PARTITION p49 VALUES LESS THAN (TO_DAYS ('2017-02-01')),PARTITION p50 VALUES LESS THAN (TO_DAYS ('2017-03-01')),PARTITION p51 VALUES LESS THAN (TO_DAYS ('2017-04-01')),PARTITION p52 VALUES LESS THAN (TO_DAYS ('2017-05-01')),PARTITION p53 VALUES LESS THAN (TO_DAYS ('2017-06-01')),PARTITION p54 VALUES LESS THAN (TO_DAYS ('2017-07-01')),PARTITION p55 VALUES LESS THAN (TO_DAYS ('2017-08-01')),PARTITION p56 VALUES LESS THAN (TO_DAYS ('2017-09-01')),PARTITION p57 VALUES LESS THAN (TO_DAYS ('2017-10-01')),PARTITION p58 VALUES LESS THAN (TO_DAYS ('2017-11-01')),PARTITION p59 VALUES LESS THAN (TO_DAYS ('2017-12-01')),PARTITION p60 VALUES LESS THAN (TO_DAYS ('2018-01-01')),PARTITION p61 VALUES LESS THAN (TO_DAYS ('2018-02-01')),PARTITION p62 VALUES LESS THAN (TO_DAYS ('2018-03-01')),PARTITION future VALUES LESS THAN MAXVALUE);