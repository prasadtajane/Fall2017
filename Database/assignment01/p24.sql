select 	distinct a.name as artist_name
from 	Playlist pl inner join PlaylistTrack plt
							inner join Track t
							inner join Album alb
							inner join Artist a
			on	pl.playlistid = plt.playlistid
			and	plt.trackid = t.trackid
			and	t.albumid = alb.albumid
			and	alb.artistid = a.artistid
where pl.name = 'Heavy Metal Classic'
order by	a.name asc