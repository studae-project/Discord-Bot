CC = clang
CFLAGS += -Wall -Wpedantic -Wwrite-strings -Ofast -Iinclude/
LFLAGS += -ldiscord -lcurl -lpthread -pthread -Llib/

SRC = $(wildcard src/*.c) $(wildcard src/**/*.c)
OBJ = $(SRC:.c=.o)

all: compile

%.o: %.c
	$(CC) $(CFLAGS) -c -o $@ $<

compile: $(OBJ)
	$(CC) $(CFLAGS) $(LFLAGS) -o bot $(OBJ)

run: bot
	./$<

clean:
	rm $(OBJ) -rfv 2> /dev/null || true
