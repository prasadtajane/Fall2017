select	pl2.name as artist_name,
			pl2.num_tracks as num_tracks
from	(
			select	art.name,
						q.artistId, 
						sum(q.tracks_per_album) as num_tracks
			from	Artist art 
						inner join 
						(
						select	t.albumid as albumId, 
									count(t.trackId) as tracks_per_album, 
									alb.artistId as artistId
						from	track t 
									natural join 
									album alb
						group by	t.albumid 
						order by	t.albumid
						) q
						on	art.artistId = q.artistId
			group by	art.artistId
			order by	num_tracks desc
			) pl2
where	pl2.num_tracks > 50
			and pl2.name 
				not in (
							'Various Artists',
							'The Office',
							'Lost'
							)


/*
select * from track where albumId in (
select albumId from album where artistId in (
select artistId from Artist where name in ('Iron Maiden')
))
*/