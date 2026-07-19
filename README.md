# mnemos-cli
A Scala cli to reduce cognitive load of remembering when to review information inside or about Memory palaces. Named after Mnemosyne, the Greek godess of memory.
## Features
Schedules reminders for Memory Palace entries—and the Memory Palaces themselves—using an expanding retrieval schedule.
## Roadmap API
```{bash}mem "learned implicits" -t "scala" -p "home_floor1"```
--tag tag the topic of the memory
--palace link to memory palace where stored (if no other arguments besides --palace, inferred as reminder to learn palace)
```{bash}mem report```
shows current state of memories in table, and upcoming notifications
--tag filter to certain tags
--palace filter to certain palaces, palace itself shows up at top
```{bash}mem log "learned implicits"```
record log of specific memory
```{bash}mem loci```
rehearse list of pending memories, Y/N to count as rehearsed. (Could open in editor? mark X - similar to how git merges are done)



### Modes of reminders
All memories run with a default of expanding retrieval
Memory Palace only memories run with an optional, Forward, Backward, Middle-Forward, Middle-Backward, Random reminders

### Updating memories?
Open editor to edit csv, or can we overwrite tag, note, or palace?

#### Building
```{bash}
scala-cli package --jvm 17 . -f
```
