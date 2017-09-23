select	distinct alb.title as album_title
from	Genre g 	inner join Track t
							inner join album alb
			on	g.genreid = t.genreid
			and	t.albumid = alb.albumid
where	g.name like '%Bossa Nova%'