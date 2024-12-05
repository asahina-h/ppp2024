import random

red_dis = '\033[31m'
end_dis = '\033[0m'

# キャラクターのステータス
class Character:
    def __init__(self, name, strength, intelligence, dexterity, sanity):
        self.name = name
        self.strength = strength
        self.intelligence = intelligence
        self.dexterity = dexterity
        self.hp = 100  # ヒットポイント
        self.sanity = sanity  # 精神的健康度（Sanity）
        self.attack_power = self.strength * 2

    def attack(self, target):
        """キャラクターの攻撃（HPのみ）"""
        roll = random.randint(1, 100)
        
        if roll <= 5:  # クリティカル (1-5)
            damage = random.randint(5, 15) + self.attack_power
            damage *= 2  # ダメージが2倍
            target.hp -= damage
            print(f"【クリティカル！】{self.name}の攻撃！ {target.name}に" + red_dis + f"{damage}" + end_dis + "ダメージ！")
        elif roll >= 96:  # ファンブル (96-100)
            print(f"【ファンブル！】{self.name}の攻撃が外れました！")
        else:
            damage = random.randint(5, 15) + self.attack_power
            target.hp -= damage
            print(f"{self.name}の攻撃！ {target.name}に" + red_dis + f"{damage}" + end_dis + "ダメージ！")

    def is_alive(self):
        return self.hp > 0

    def lose_sanity(self, amount):
        """精神的健康度を減らす"""
        self.sanity -= amount
        if self.sanity <= 0:
            print(red_dis + f"{self.name}は完全に狂気に陥り、精神が崩壊しました..." + end_dis)
            return False
        return True

    def sanity_check(self):
        """Sanityチェックを行う"""
        roll = random.randint(1, 100)
        if roll <= 50:  # 50%の確率でチェック成功
            print(f"{self.name}はSanityチェックに成功しました！精神的ダメージは軽減されました。")
            return True
        else:
            return self.terrible_event()  # 失敗した場合、恐ろしい出来事が発生

    def terrible_event(self):
        """Sanityチェック失敗時の恐ろしい出来事を発生させる"""
        event = random.choice(["ancient_artifact", "sight_of_cthulhu", "ritual", "madness"])
        
        if event == "ancient_artifact":
            print(red_dis + f"{self.name}は不気味な古代のアーティファクトを見つけてしまった！\n　SAN値：-5" + end_dis)
            return self.lose_sanity(5)
        
        elif event == "sight_of_cthulhu":
            print(red_dis + f"{self.name}はクトゥルフの姿を一瞬だけ目撃してしまった！\n　SAN値：-20" + end_dis)
            return self.lose_sanity(20)  # クトゥルフ目撃によるSanity減少
        
        elif event == "ritual":
            print(red_dis + f"{self.name}の頭の中に、邪教の儀式の映像が鮮明に送られてきた！その恐ろしい光景が脳裏に焼き付いていく。\n　SAN値：-10" + end_dis)
            return self.lose_sanity(10)
        
        else:
            print(red_dis + f"{self.name}は不可解な音を聞き、狂気に近づいている！\n　SAN値：-15" + end_dis)
            return self.lose_sanity(15)

# 邪神のクラス
class ElderGod(Character):
    def __init__(self):
        super().__init__(name="邪神", strength=50, intelligence=100, dexterity=20, sanity=200)

    def attack(self, target):
        """邪神の攻撃（HPダメージとSanityダメージ）"""
        roll = random.randint(1, 100)
        
        if roll <= 5:  # クリティカル
            damage = random.randint(10, 20) + self.attack_power
            damage *= 2  # ダメージ2倍
            target.hp -= damage
            target.lose_sanity(damage // 2)
            print(f"【邪神のクリティカル！】邪神の攻撃！ {target.name}に" + red_dis + f"{damage}" + end_dis + f"ダメージ！Sanityダメージ: {damage // 2}")
            # クリティカル時のSanityチェック
            target.sanity_check()  # プレイヤーのSanityチェック
        elif roll >= 96:  # ファンブル
            print(f"【邪神のファンブル！】邪神の攻撃が外れました！")
        else:
            damage = random.randint(10, 20) + self.attack_power
            target.hp -= damage
            target.lose_sanity(damage // 2)
            print(f"邪神の攻撃！ {target.name}に" + red_dis + f"{damage}" + end_dis + f"ダメージ！Sanityダメージ: {damage // 2}")

# 洋館の部屋を探索する機能
class Mansion:
    def __init__(self):
        self.rooms = {
            "1階のホール": {"調査可能": ["本棚", "窓", "床"], "clue_found": False},
            "1階食堂": {"調査可能": ["テーブル", "椅子", "床"], "clue_found": False},
            "1階書斎": {"調査可能": ["本棚", "机", "窓"], "clue_found": False},
            "2階寝室": {"調査可能": ["ベッド", "窓", "床"], "clue_found": False},
            "地下室": {"調査可能": ["棚", "床", "壁"], "clue_found": False},
            "図書室": {"調査可能": ["本棚", "机", "窓"], "clue_found": False},
        }
        self.clue_count = 0  # 発見した手がかりの数

    def explore_room(self, room, player):
        if room not in self.rooms:
            print("その部屋は存在しません。")
            return

        print(f"\n{room}に移動しました。")
        
        while True:
            print(f"調査できるもの: {', '.join(self.rooms[room]['調査可能'])}")
            action = input("調べたい場所を入力してください（例：本棚、窓、床など）: ")

            if action in self.rooms[room]["調査可能"]:
                self.check_clue(room, action, player)
            else:
                print("その場所は調べられません。")

            # もし再探索したい場合、再度選べるように
            repeat_action = input(f'{room}の別の場所を調べますか？(はい/いいえ): ')
            if repeat_action.lower() == 'いいえ':
                break  # 探索が終了し、次の選択肢に進む
            elif repeat_action.lower() == 'はい':
                continue  # 再度同じ部屋を探索するためwhileループを続ける

    def check_clue(self, room, action, player):
        """手がかりを調べる"""
        roll = random.randint(1, 100)
        if roll <= player.intelligence * 10:  # 知力が高いほど成功しやすく
            print(f"{player.name}は{action}を調べて手がかりを見つけました！")
            print(red_dis + '手がかりは邪神の存在を示唆しています。' + end_dis)
            player.sanity_check()  # 邪神の片鱗に触れ、sanチェック
            if not self.rooms[room]["clue_found"]:
                self.clue_count += 1
                self.rooms[room]["clue_found"] = True
            else:
                print(f"{room}ではすでに手がかりを見つけています。")
        else:
            print(f"{player.name}は{action}を調べたが、手がかりを見つけられなかった。")

    def all_clues_found(self):
        return self.clue_count >= 5  # 手がかりが5つ見つかったらクリア

    def choose_room(self):
        """部屋を選んで探索する"""
        available_rooms = [room for room in self.rooms if not self.rooms[room]["clue_found"]]
        print(f"\n現在の発見済み手がかり数: {self.clue_count}")
        print("探索可能な部屋: ")
        for i, room in enumerate(available_rooms, 1):
            print(f"{i}. {room}")
        
        while True:
            try:
                choice = int(input("部屋の番号を選んでください: "))
                if 1 <= choice <= len(available_rooms):
                    return available_rooms[choice - 1]  # 部屋を選ぶ
                else:
                    print("無効な番号です。再度選んでください。")
            except ValueError:
                print("番号を入力してください。")

# ゲーム開始の関数
def start_game():
    # プレイヤー情報の入力
    name = input("キャラクターの名前を入力してください: ")

    # ステータスの入力
    while True:
        try:
            print('筋力・知力・俊敏のステータスを設定します。ステータスの合計値が15を超えることはできません。')
            strength = int(input("筋力を1から10の範囲で入力してください: "))
            intelligence = int(input("知力を1から10の範囲で入力してください: "))
            dexterity = int(input("俊敏を1から10の範囲で入力してください: "))

            if strength + intelligence + dexterity > 15:
                print("合計値が15を超えました。再度入力してください。")
            else:
                break
        except ValueError:
            print("数値を入力してください。")

    # プレイヤーの生成
    player = Character(name, strength, intelligence, dexterity, sanity=100)
    mansion = Mansion()

    #--------キャラクターを作成した後に導入テキストが出る-----------

    print(red_dis + """ゲームを開始します。

あなたは古びた洋館に足を踏み入れました。薄暗く、冷たい空気が漂う中、どこからか不吉な音が聞こえてきます。
壁に映る陰がひときわ大きく、あなたの不安を煽ります。
この洋館には恐ろしい秘密が隠されており、手がかりを集めてその真実に迫ることが求められます。
しかし、あなたが探し出すべきものは、ただの謎ではありません。
恐怖と狂気の中で、次第にその存在が明らかになるでしょう。""" + end_dis)

    # 初めに「1階のホール」から探索を始める
    current_room = "1階のホール"
    mansion.explore_room(current_room, player)

    # 部屋を選んで探索
    while not mansion.all_clues_found():
        current_room = mansion.choose_room()  # プレイヤーが部屋を選択
        mansion.explore_room(current_room, player)

    # 手がかりを5つ集めたら邪神との戦闘
    print(f"\n{player.name}、すべての手がかりを集めました！邪神との戦闘が始まります！")
    elder_god = ElderGod()

    # 戦闘ループ
    while player.is_alive() and elder_god.is_alive():
        print(f"\n{player.name}のターンです。攻撃します！")
        player.attack(elder_god)

        if not elder_god.is_alive():
            print(f"\n{player.name}は邪神を倒しました！\n{player.name}は生還しました！")
            break

        print("\n邪神のターンです。")
        elder_god.attack(player)

        if not player.is_alive():
            print(f"\n{player.name}は死亡しました...ゲームオーバー！")
            break

if __name__ == "__main__":
    start_game()
