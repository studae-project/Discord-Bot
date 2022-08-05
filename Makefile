CC = clang
CFLAGS += -Wall -Wpedantic -Wwrite-strings -Ofast -Iinclude/
LFLAGS += -lcurl -ldiscord -lpthread -pthread

SRC = $(wildcard src/*.c) $(wildcard src/**/*.c)
OBJ = $(SRC:.c=.o)

all: compile

%.o: %.c
	$(CC) $(CFLAGS) -c -o $@ $<

compile: $(OBJ)
	$(CC) -o bot $(OBJ) $(CFLAGS) $(LFLAGS)

run: bot
	./$<

clean:
	rm $(OBJ) -rfv 2> /dev/null || true
