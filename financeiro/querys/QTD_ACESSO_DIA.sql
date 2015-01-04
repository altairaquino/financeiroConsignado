SELECT cast(a.LGDENTR as date), count(*)
FROM VW_LOG a
group by cast(a.LGDENTR as date)
order by cast(a.LGDENTR as date) desc