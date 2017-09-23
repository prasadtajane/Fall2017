select	alb.title as album_title, 
			t.trackId as track_id, 
			t.name as track_name, 
			cast(round(t.milliseconds/60000) AS INTEGER) as minutes, 
			cast(round((t.milliseconds - round(t.milliseconds/60000)*60000)/1000) AS INTEGER) as seconds
from	Artist a	inner join Album alb 
							inner join track t
			on 	a.ArtistId = alb.ArtistId 
			and 	t.albumid = alb.albumid
where 	a.name like '%The Black Crowes%'
order by 	alb.title ASC, 
				t.trackId ASC
	