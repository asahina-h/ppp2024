package com.example.animalfortune;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnimalFortuneController
{

	// 年、月、日を動的に生成する
	@GetMapping("/")
	public String showForm(@RequestParam(value = "month", required = false) String month, Model model)
	{
		// 年リストを1900年から今年までに設定
		List<String> years = getYearsRange();
		model.addAttribute("years", years);

		// 月リストを日本語（1月〜12月）で設定
		List<String> months = getMonths();
		model.addAttribute("months", months);

		// デフォルトは1月に設定
		if (month == null || month.isEmpty())
		{
			month = "1";
		}

		// 月ごとの最大日数を設定
		List<Integer> daysInMonth = getDaysInMonth(month);
		model.addAttribute("daysInMonth", daysInMonth); // 修正: List<Integer> に変更
		model.addAttribute("selectedMonth", month);

		return "index"; // index.htmlを返す
	}

	// 年の範囲（今年から1900年まで）
	private List<String> getYearsRange()
	{
		List<String> years = new ArrayList<>();
		int currentYear = LocalDate.now().getYear();
		for (int i = currentYear; i >= 1900; i--)
		{
			years.add(String.valueOf(i)); // 年をリストに追加
		}
		return years;
	}

	// 月リスト（1〜12）
	private List<String> getMonths()
	{
		List<String> months = new ArrayList<>();
		for (int i = 1; i <= 12; i++)
		{
			months.add(String.valueOf(i)); // 1から12までを追加
		}
		return months;
	}

	// 月ごとの最大日数を設定する
	private List<Integer> getDaysInMonth(String month)
	{
		List<Integer> daysInMonth = new ArrayList<>();
		int maxDays = 31; // 基本は31日
		switch (month)
		{
		case "2": // 2月は28日または29日（閏年を考慮）
			int year = LocalDate.now().getYear();
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
			{
				maxDays = 29; // 閏年
			} else
			{
				maxDays = 28; // 通常の2月
			}
			break;
		case "4":
		case "6":
		case "9":
		case "11":
			maxDays = 30; // 30日の月
			break;
		default:
			maxDays = 31; // 31日までの月
		}

		// 1日からmaxDaysまでをリストに追加
		for (int i = 1; i <= maxDays; i++)
		{
			daysInMonth.add(i); // 1〜最大日数を追加
		}

		return daysInMonth;
	}

	// 占い結果を表示する処理（年、月、日を受け取って運勢を計算）
	@GetMapping("/fortune")
	public String getFortune(@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "day", required = false) String day,
			Model model)
	{

		// 年がnullまたは空の場合、デフォルト値として現在の年を設定
		if (year == null || year.isEmpty())
		{
			year = String.valueOf(LocalDate.now().getYear());
		}

		// 月がnullまたは空の場合、デフォルト値として1月を設定
		if (month == null || month.isEmpty())
		{
			month = "1"; // 1月
		}

		// 日がnullまたは空の場合、デフォルト値として1日を設定
		if (day == null || day.isEmpty())
		{
			day = "1"; // 1日
		}

		// 年を整数に変換
		int yearValue = Integer.parseInt(year);

		// 運勢の計算（12で割った余りを利用）
		List<AnimalFortune> fortunes = getAnimalFortunes();
		int fortuneIndex = (yearValue % 12);

		// fortuneIndexがリストのサイズを超えないように制限
		fortuneIndex = fortuneIndex % fortunes.size(); // 修正

		// fortuneIndexが負の値になることを防ぐ
		if (fortuneIndex < 0)
		{
			fortuneIndex += fortunes.size(); // 修正
		}

		AnimalFortune fortune = fortunes.get(fortuneIndex);
		model.addAttribute("fortune", fortune);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);

		return "fortune"; // 結果ページを表示
	}

	// 動物占いのデータ
	private List<AnimalFortune> getAnimalFortunes()
	{
		List<AnimalFortune> fortunes = new ArrayList<>();
		fortunes.add(new AnimalFortune("長生", "人が生まれたとき。生長していく喜び。", "法皇", "新しい可能性を学ぶ", "サル"));
		fortunes.add(new AnimalFortune("沐浴", "産湯を使う。裸になるところから色情も表す。", "恋人", "恋愛をして結ばれる", "チータ"));
		fortunes.add(new AnimalFortune("冠帯", "一人前になり、社会に出る。", "戦車", "20才になり兵役へ", "くろひょう"));
		fortunes.add(new AnimalFortune("建録", "官職に就き、一人前になる。最高運。", "力", "社会で戦う", "ライオン"));
		fortunes.add(new AnimalFortune("帝旺", "気力旺盛なとき。事業の完成、そして衰えへ。", "隠者", "頂点から隠遁生活へ", "トラ"));
		fortunes.add(new AnimalFortune("衰", "やがて衰退へ向かう。", "正義", "過去を裁かれる", "たぬき"));
		fortunes.add(new AnimalFortune("病", "病気にかかり衰退する", "死神", "心身共に弱くなる", "くま"));
		fortunes.add(new AnimalFortune("死", "死亡し、もう生きられない", "月", "覚悟が必要", "キリン"));
		return fortunes;
	}
}
