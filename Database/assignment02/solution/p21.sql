select	name as lazy_artist 
from	Artist 
where	artistId not in (
	select	distinct artistid 
	from	Album
	)
order by	name