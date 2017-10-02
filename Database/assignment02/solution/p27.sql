select 	distinct a.name as distinguished_artist
from	Track t
				inner join
			Genre g
				inner join
			Album alb
				inner join
			Artist a
			on 	t.genreId = g.genreId
			and	alb.albumId = t.albumId
			and	a.artistId = alb.artistId
where	g.name <> 'Classical'
and		t.trackId in	(
			select 	trackId
			from	PlaylistTrack
			group by	trackId
			having 	count(*) > 3
			and			trackId in	(
						select 	trackId
						from	InvoiceLine
						group by 	trackId
						having 	count(*) > 1
			)
)
order by	a.name 