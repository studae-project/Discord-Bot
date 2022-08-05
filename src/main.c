#include <stdio.h>
#include <stdlib.h>
#include <concord/discord.h>

int main(int argc, char **argv) {
  char *token = getenv("TOKEN");

  if (token == NULL) {
    if (argc <= 1) {
      log_error("Usage: %s <TOKEN>\n/!\\ Or set an enviroment variable called TOKEN.", argv[0]);
      return 1;
    }

    token = argv[1];
  }

  struct discord *bot = discord_init(token);

  if (bot == NULL) {
    log_error("Cannot create discord bot instance!");
    return 1;
  }

  // TODO: Register some events and commands here

  discord_run(bot);
  discord_cleanup(bot);

  return 0;
}
